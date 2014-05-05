package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class PanierController {

    def springSecurityService
    def daoAuthenticationProvider
	def mailService
	def shrimpsMailService

    def index() {
        redirect action: 'index'
    }

    def merci() {}

    def paypalresponse() {
        log.debug request.characterEncoding
        log.debug params
        //def url = grailsApplication.config.paypal.url + "?cmd=_notify-validate&" + params.collect { it }.join('&')
        //log.debug url
        //log.debug url.toURL().getText(request.characterEncoding)
        //log.debug url.toURL().getText(params.charset)

        def urlString = grailsApplication.config.paypal.url
        def queryString = "cmd=_notify-validate&" + params.collect { it }.join('&')

        def url = new URL(urlString)
        def connection = url.openConnection()
        connection.setRequestMethod("POST")
        connection.doOutput = true

        def writer = new OutputStreamWriter(connection.outputStream)
        writer.write(queryString)
        writer.flush()
        writer.close()
        connection.connect()

        def recaptchaResponse = connection.content.text
        log.debug(recaptchaResponse)

        def commande = Commande.get(params.invoice)
        
        if(params.receiver_email != grailsApplication.config.paypal.receiver) {
            log.error "pas le bon receiver"
            log.error params
            return
        }

        if (commande) {
            commande.paypalTransactionId = params.txn_id
            
            if (params.payment_status == "Completed" && commande.statut != "expédiée") {
                commande.statut = "payée"
				
				commande.lignes.each { LigneCommande ligne ->
					ligne.produit.stock -= ligne.quantite
				}
				
				def msg = "Bonjour,<br/>Merci de votre commande sur shrimpsforall.fr<br/>Elle sera traitée dans les meilleurs délais.".encodeAsHTML()
				
				shrimpsMailService.configure()
				
				mailService.sendMail {
					to "julien.rott@gmail.com"
					subject "Votre commande shrimpsforall.fr est validée"
					body view: '/shared/mailCommande', model: [commande: commande, msg: msg]
				}
            }
            if (params.payment_status == "Pending") {
                commande.statut = "paiement en validation"
            }
        }

        render 200
    }

    def indexFlow = {
        showPanier {
            render view: 'index'
            on("commander").to "commander"
        }
        commander {
            action {
                if(springSecurityService.isLoggedIn()) adresse()
                else loginPage()
            }
            on("adresse"){conversation.showBreadCrumb = true}.to "toAdresse"
            on("loginPage").to "loginPage"
        }
        loginPage {
            render view: 'loginPage'
            on("connect").to "connect"
            on("showCreateAccount"){conversation.showBreadCrumb = true}.to "showCreateAccount"
        }
        connect {
            action {
                try {
                    def auth = new UsernamePasswordAuthenticationToken(params.j_username2, params.j_password2)
                    def authtoken = daoAuthenticationProvider.authenticate(auth)
                    SecurityContextHolder.context.authentication = authtoken
                    adresse()
                }
                catch(Exception e) {
                    if (e.message == "Bad credentials")
                        flash.message = message(code: 'springSecurity.errors.login.fail')
                }
                
            }
            on("adresse"){conversation.showBreadCrumb = true}.to "toAdresse"
            on(Exception).to "loginPage"
        }
        showCreateAccount {
            subflow controller: "home", action: "newAccount"
            on("success").to "toAdresse"
        }
        toAdresse {
            action {
                User user = User.findByUsername(springSecurityService.getCurrentUser().getUsername())
                
                Commande commande = new Commande(
                    nom: user.nom,
                    prenom: user.prenom,
                    adresse: user.adresse.ligne1,
                    complAdresse: user.adresse.ligne2,
                    codePostal: user.adresse.codePostal,
                    ville: user.adresse.ville,
                    statut: "création",
                    client: user,
                    fraisPort: session.panier?.totaux()?.totalFraisPort)

                session.panier.lignes.each {
                    def produit = Produit.get(it.produit?.id)
                    def lot = Lot.get(it.lot?.id)

                    LigneCommande ligneCommande = new LigneCommande(commande: commande, 
                                                                    produit: produit, 
                                                                    lot: lot,
                                                                    quantite: it.quantite,
                                                                    prix: it.lot ? it.lot.prix : it.produit.prix,
                                                                    titre: "${it.lot?.titre} ${it.produit.titre}")
                    
                    commande.addToLignes(ligneCommande)
                }

                commande.save()

                user.addToCommandes(commande)

                if (!user.save()) {
                    user.errors.each {
                        println it
                    }
                }

                conversation.user = user
                conversation.commande = commande
                session.panier = null
            }
            
            on("success").to "adresse"
        }
        adresse {
            redirect controller: "commande", action: "details", id: conversation.commande.id
        }
    }

    def vider() {
    	session.panier = null
    	render template: 'panier'
    }

    def delrow() {
        def panier = session.panier
        def produit = Produit.get(params.id)
        def idLot = params.idLot as long
        def iterator = panier.lignes.iterator()

        while(iterator.hasNext()) {
            def ligne = iterator.next()
            if(idLot > 0) {
                if(ligne.lot?.id == idLot) {
                    iterator.remove()
                }
            }
            else if(ligne.produit.id == params.id as long) {
                iterator.remove()
            }
        }

        redirect action: 'index'
    }

    def inc() {
        def panier = session.panier
        def produit = Produit.get(params.id)
        def idLot = params.idLot as long

        panier.lignes.each { ligne ->
            if(idLot > 0) {
                if(ligne.lot?.id == idLot) {
                    ligne.quantite += 1
                }
            }
            else if(ligne.produit.id == params.id as long) {
                ligne.quantite += 1
            }
        }

        redirect action: 'index'
    }

    def dec() {
        def panier = session.panier
        def produit = Produit.get(params.id)
        def idLot = params.idLot as long

        panier.lignes.each { ligne ->
            if(idLot > 0) {
                if(ligne.lot?.id == idLot && ligne.quantite > 1) {
                    ligne.quantite -= 1
                }
            }
            else if(ligne.produit.id == params.id as long && ligne.quantite > 1) {
                ligne.quantite -= 1
            }
        }

        redirect action: 'index'
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def add() {
    	log.debug params

    	def panier = session.panier?:new Panier()
        def idProduit = params.idProduit as long
    	def produit = Produit.get(idProduit)
        def quantite = params.quantite as int
    	def added = false
        def idLot = params.idLot as long

        panier.lignes.each { ligne ->
            if (idLot > 0) {
    			if(ligne.lot?.id == idLot) {
    				ligne.quantite += quantite
    				added = true
    			}
            }
            else {
                if(ligne.produit.id == idProduit) {
                    ligne.quantite += quantite
                    added = true
                }
            }
    	}

    	if(!added) {
    		LignePanier ligne = new LignePanier(produit: produit, quantite: quantite, lot: idLot > 0 ? Lot.get(idLot) : null)

    		if(!panier.lignes) {
	    		panier.lignes = []
    		}
    		
            panier.lignes.add(ligne)
    	}

		session.panier = panier

    	render template: 'panier'
    }
}
