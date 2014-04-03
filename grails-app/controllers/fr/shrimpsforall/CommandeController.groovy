package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class CommandeController {

	def springSecurityService

    def index() { }

    def mescommandes() {
    	User user = User.findByUsername(springSecurityService.getCurrentUser().getUsername())
    	def commandes = Commande.createCriteria().list(sort: 'dateCreated', order: 'desc'){
    		eq "client", user
    	}
    	render view: "listeCommandes", model: [commandes: commandes, titre: "Mes Commandes"]
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

        payer{
            render view: "/shared/_showCommande"
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
        redirect action: "aexpedier"
    }
}
