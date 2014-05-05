package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment;

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class HomeController {

    def springSecurityService
	def mailService
	def shrimpsMailService

    def index() {
        [homePageSlider: HomePageSlider.get(1)]
    }

    def createAccount() {
    	redirect action: 'create'
    }

    def createFlow = {
        create {
            subflow controller: "home", action: "newAccount"
            on("success").to "adresse"
        }
        adresse {
            subflow controller: "home", action: "adresse"
            on("success").to "index"
        }
        index{
            redirect action: "index"
        }
    }

    def newAccountFlow = {
    	showCreateAccount {
            //render view: 'newAccount'
            on("createNewAccount").to "createNewAccount"
        }
        createNewAccount {
            action {
                if( params.j_username2.equals(params.j_username2_confirm) && 
                    params.j_password2.equals(params.j_password2_confirm) ) {
                    
                    User user = new User(username: params.j_username2, password: params.j_password2)
                    if (!user.save()) {
                    	user.errors.each{println it}
                        flow.user = user
                        error()
                    }
                    else {
                    	springSecurityService.reauthenticate user.username
						
						def msg = "<p>Bonjour,</p><p>Vous venez de créer un compte sur shrimpsforall.fr</p><p>Votre identifiant : ${user.username}</p>".encodeAsHTML()
						
						shrimpsMailService.configure()
						
						def mails = []
						if (Environment.current == Environment.PRODUCTION) {
							mails = ["shrimpsforall@outlook.fr", user.username]
						}
						else if (Environment.current == Environment.DEVELOPMENT) {
							mails = ["julien.rott@gmail.com", user.username]
						}
						
						mailService.sendMail {
							to mails
							subject "Vous venez de créer un compte sur shrimpsforall.fr"
							body view: '/shared/mailCreationCompte', model: [msg: msg]
						}
                	}
                }
                else {
                    User user = new User(username: params.j_username2, password: params.j_password2)
                    flow.user = user
                    flash.message = "Les données ne correspondent pas"
                    error()
                }
            }
            on("error").to "showCreateAccount"
            on(Exception).to "showCreateAccount"
            on("success").to "success"
        }
        success()
    }

    def adresseFlow = {
        displayAdresse {
            render view: 'adresse'
            on("createAdresse").to "createAdresse"
        }

        createAdresse {
            action {
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
            }
            on("error").to "displayAdresse"//garder les données saisies
            on("success").to "success"
        }
        success()
    }

    @Secured(['ROLE_ADMIN'])
    def clients() {
        [clients: User.list()]
    }
}
