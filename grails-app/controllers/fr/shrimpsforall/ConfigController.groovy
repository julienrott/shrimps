package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment;

@Secured(['ROLE_ADMIN'])
class ConfigController {
	
	def mailService
	def shrimpsMailService

    def index() {
		Config config = Config.get(1)
		if(!config) {
			config = new Config()
		}
		config.mailPassword = new String(config.mailPassword.decodeBase64())
		config.discard()
		[config: config]
	}
	
	def save() {
		Config config = Config.get(1)
		params.mailPassword = params.mailPassword.bytes.encodeBase64().toString()
		
		if(!config) {
			config = new Config(params)
		}
		else {
			config.properties = params
		}
		
		def msg = ""
		if(!config.save()) {
			config.errors.each {
				msg += it
			}
			flash.message = msg
		}
		
		redirect action: "index"
	}
	
	def sendMailTest() {
		shrimpsMailService.configure()
		
		def msg = "<p>Bonjour,<p></p>Votre commande shrimpsforall.fr vient d'être expédiée</p>".encodeAsHTML()
		
		def mails = []
		if (Environment.current == Environment.PRODUCTION) {
			mails = ["shrimpsforall@outlook.fr", "julien.rott@gmail.com"]
		}
		else if (Environment.current == Environment.DEVELOPMENT) {
			mails = ["julien.rott@gmail.com"]
		}
		
		mailService.sendMail {
			to mails
			subject "hello world"
			body view: '/shared/mailCommande', model: [msg: msg]
		}
		
		render 200
	}
}
