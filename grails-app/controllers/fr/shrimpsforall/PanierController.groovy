package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import com.braintreegateway.*

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class PanierController {

    def springSecurityService
    def daoAuthenticationProvider

    def index() {
        redirect action: 'index'
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
                //[user: user]
                conversation.user = user
            }
            
            on("success").to "adresse"
        }
        adresse {
            subflow controller: "home", action: "adresse"
            on("success") {
                [user: User.findByUsername(springSecurityService.getCurrentUser().getUsername())]
            }.to "showPaiement"
        }
        showPaiement {
            on("retour").to "toAdresse"
            on("payer").to "payer"
        }
        payer {
            action {
                BraintreeGateway gateway = new BraintreeGateway(
                    Environment.SANDBOX,
                    "g56253882wvypmk5",//"your_merchant_id",
                    "tdx6wf4v8swb2xv4",//"your_public_key",
                    "cce57d5cbf3e7c227a584f9c17b15bea"//"your_private_key"
                )

                TransactionRequest transactionRequest = new TransactionRequest()
                    .amount(new BigDecimal("1000.00"))
                    .creditCard()
                        .number(params.number)
                        .cvv(params.cvv)
                        .expirationMonth(params.month)
                        .expirationYear(params.year)
                        .done()
                    .options()
                        .submitForSettlement(true)
                        .done();

                Result<Transaction> result = gateway.transaction().sale(transactionRequest);
                
                if (result.isSuccess()) {
                    User user =  User.findByUsername(springSecurityService.getCurrentUser().getUsername())
                    Commande commande = new Commande(statut: "payée", client: user)
                    user.addToCommandes(commande)

                    session.panier.lignes.each {
                        LigneCommande ligneCommande = new LigneCommande(commande: commande, 
                                                                        produit: it.produit, 
                                                                        quantite: it.quantite,
                                                                        prix: it.produit.prix,
                                                                        fraisPort: it.produit.fraisPort,
                                                                        titre: it.produit.titre)
                        commande.addToLignes(ligneCommande)
                    }

                    commande.save(flush: true)
                    user.save(flush: true)
                    session.panier = null
                    return success()
                }
                else {
                    flash.message = "Erreur lors du paiement, veuillez réessayer"
                    return error()
                }
            }
            on("success").to "confirmation"
            on("error").to "showPaiement"
            on(Exception).to "showPaiement"
        }
        confirmation()
    }

    def vider() {
    	session.panier = null
    	render template: 'panier'
    }

    def delrow() {
        def panier = session.panier
        def produit = Produit.get(params.id)
        def iterator = panier.lignes.iterator()

        while(iterator.hasNext()) {
            def ligne = iterator.next()
            if(ligne.produit.id == params.id as long) {
                iterator.remove()
            }
        }

        redirect action: 'index'
    }

    def inc() {
        def panier = session.panier
        def produit = Produit.get(params.id)

        panier.lignes.each { ligne ->
            if(ligne.produit.id == params.id as long) {
                ligne.quantite += 1
            }
        }

        redirect action: 'index'
    }

    def dec() {
        def panier = session.panier
        def produit = Produit.get(params.id)

        panier.lignes.each { ligne ->
            if(ligne.produit.id == params.id as long) {
                if(ligne.quantite > 1) {
                    ligne.quantite -= 1
                }
            }
        }

        redirect action: 'index'
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def add() {
    	log.debug params

    	def panier = session.panier?:new Panier()
    	def produit = Produit.get(params.id)
        def quantite = params.quantite as int
    	def added = false

		panier.lignes.each { ligne ->
			if(ligne.produit.id == params.id as long) {
				ligne.quantite += quantite
				added = true
			}
    	}

    	if(!added) {
    		LignePanier ligne = new LignePanier(produit: produit, quantite: quantite)
    		if(!panier.lignes) {
	    		panier.lignes = []
    		}
    		panier.lignes.add(ligne)
    	}

		session.panier = panier

    	render template: 'panier'
    }
}
