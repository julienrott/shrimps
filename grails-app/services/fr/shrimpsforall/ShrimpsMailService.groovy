package fr.shrimpsforall

import grails.transaction.Transactional

@Transactional
class ShrimpsMailService {
	
	def grailsApplication

    def configure() {
		Config config = Config.get(1)
		
		def mailSender = grailsApplication.mainContext.mailSender
		
		mailSender.setHost(config.mailHost)
		mailSender.setPort(config.mailPort)
		mailSender.setUsername(config.mailUsername)
		
		def decoded = new String(config.mailPassword.decodeBase64())
		mailSender.setPassword(decoded)
		
		def props = new Properties()
		props.putAll(Eval.me(config.mailProperties))
		mailSender.setJavaMailProperties(props)
    }
}
