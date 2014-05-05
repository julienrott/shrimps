package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment;

import com.stripe.*
import com.stripe.model.*

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class CommandeController {

	def springSecurityService
	def mailService
	def shrimpsMailService

    def index() { }

    def mescommandes() {
        def currentUser = springSecurityService.getCurrentUser()
        if(currentUser) {
        	User user = User.findByUsername(currentUser.getUsername())
        	def commandes = Commande.createCriteria().list(sort: 'dateCreated', order: 'desc'){
        		eq "client", user
        	}
        	render view: "listeCommandes", model: [commandes: commandes, titre: "Mes Commandes"]
        }
        else {
            redirect controller: "home"
        }
    }

    def details() {
        redirect action: "detailsCommande", params: [id: params.id]
    }

    def detailsCommandeFlow = {
        getCommande {
            action {
                User user = User.findByUsername(springSecurityService.getCurrentUser().getUsername())
                Role roleAdmin = Role.findByAuthority("ROLE_ADMIN")
                log.debug user.authorities.contains(roleAdmin)
            	def commande = Commande.get(params.id)
            	if (user.commandes.contains(commande) || user.authorities.contains(roleAdmin)) {
                    conversation.commande = commande
                    conversation.canPay = false
                }
                else {
                    error()
                }
            }
            on("success").to "showCommande"
            on("error").to "error"
            on(Exception).to "error"
        }

        showCommande {
            render view: "/shared/_showCommande"
            on("confirmerAdresse") {
                //conversation.canPay = true
                //conversation.commande = commande
            }.to "confirmerAdresse"
        }

        confirmerAdresse {
            on("payer") {
                def commande = Commande.get(conversation.commande.id)
                def user = User.findByUsername(springSecurityService.getCurrentUser().getUsername())

                user.nom = params.nom
                user.prenom = params.prenom
                
                if(user.adresse) {
                    user.adresse.properties = params
                }
                else{
                    def adresse = new Adresse(params)
                    adresse.client = user
                    user.adresse = adresse
                }

                def cansave = false

                if(user.nom.trim() != "" && user.prenom.trim() != "") {
                    cansave = true
                }

                commande.nom = user.nom
                commande.prenom = user.prenom
                commande.adresse = user.adresse.ligne1
                commande.complAdresse = user.adresse.ligne2
                commande.codePostal = user.adresse.codePostal
                commande.ville = user.adresse.ville

                
                if(!user.save(flush: true) || !cansave) {

                    if(user.nom.trim() == "") {
                        user.errors.rejectValue( 'nom', 'user.nom.nullable')
                    }

                    if(user.prenom.trim() == "") {
                        user.errors.rejectValue('prenom', 'user.prenom.nullable')
                    }

                    flow.user = user
                    return error()
                }

                conversation.commande = commande
                conversation.user = user
                conversation.canPay = true
            }.to "payer"
        }

        error {
    		redirect action: 'mescommandes'
        }

        payer {
            render view: "/shared/_showCommande"
            on("payerParCarte").to "chargeCard"
        }

        chargeCard {
            action{
                log.debug params
                log.debug grailsApplication.config.stripe.secret.key
                Stripe.apiKey = grailsApplication.config.stripe.secret.key;
                Map<String, Object> chargeParams = new HashMap<String, Object>();
                def totalInCents = conversation.commande.totaux().totalTTC*100
                chargeParams.put("amount", totalInCents.round());
                chargeParams.put("currency", "eur");
                chargeParams.put("card", params.stripeToken);
                chargeParams.put("description", "shrimpsforall.fr");
                try {
                    def charge = Charge.create(chargeParams);
                    println charge.properties
                    def commande = Commande.get(conversation.commande.id)
                    if (commande && commande.statut != "expédiée" && charge.paid == true) {
                        commande.stripeChargeId = charge.id
                        commande.statut = "payée"
						
						commande.lignes.each { LigneCommande ligne ->
							ligne.produit.stock -= ligne.quantite
						}
						
						def msg = "<p>Bonjour,</p><p>Merci de votre commande sur shrimpsforall.fr</p><p>Elle sera traitée dans les meilleurs délais.</p>".encodeAsHTML()
						
						shrimpsMailService.configure()
						
						def mails = []
						if (Environment.current == Environment.PRODUCTION) {
							mails = ["shrimpsforall@outlook.fr", commande.client.username]
						}
						else if (Environment.current == Environment.DEVELOPMENT) {
							mails = ["julien.rott@gmail.com", commande.client.username]
						}
						
						mailService.sendMail {
							to mails
							subject "Votre commande shrimpsforall.fr est validée"
							body view: '/shared/mailCommande', model: [commande: commande, msg: msg]
						}
                    }
                }
                catch(Exception e) {
                    log.error e
                    return error()                    
                }
                
            }
            on("success").to "merci"
            on("error").to "error"
        }

        merci {
            redirect controller: "panier", action: "merci"
        }
    }

    @Secured("ROLE_ADMIN")
    def encreation() {
        def commandes = Commande.createCriteria().list(sort: 'dateCreated', order: 'asc'){
            eq "statut", "création"
        }
        render view: "listeCommandes", model: [commandes: commandes, titre: "Commandes en création"]
    }

    @Secured("ROLE_ADMIN")
    def aexpedier() {
        def commandes = Commande.createCriteria().list(sort: 'dateCreated', order: 'asc'){
            eq "statut", "payée"
        }
        render view: "listeCommandes", model: [commandes: commandes, titre: "Commandes à expédier"]
    }

    @Secured("ROLE_ADMIN")
    def expediees() {
        def commandes = Commande.createCriteria().list(sort: 'dateCreated', order: 'asc'){
            eq "statut", "expédiée"
        }
        render view: "listeCommandes", model: [commandes: commandes, titre: "Commandes expédiées"]
    }

    @Secured("ROLE_ADMIN")
    def expediee() {
        def commande = Commande.get(params.id)
        commande.statut = "expédiée"
        commande.save()
		
		def msg = "<p>Bonjour,<p></p>Votre commande shrimpsforall.fr vient d'être expédiée</p>".encodeAsHTML()
		
		shrimpsMailService.configure()
		
		def mails = []
		if (Environment.current == Environment.PRODUCTION) {
			mails = ["shrimpsforall@outlook.fr", commande.client.username]
		}
		else if (Environment.current == Environment.DEVELOPMENT) {
			mails = ["julien.rott@gmail.com", commande.client.username]
		}
		
		mailService.sendMail {
			to mails
			subject "Votre commande shrimpsforall.fr est expédiée"
			body view: '/shared/mailCommande', model: [commande: commande, msg: msg]
		}
		
        redirect action: "aexpedier"
    }
}
