package fr.shrimpsforall

import grails.plugin.mail.MailService
import grails.test.spock.IntegrationSpec;
import spock.lang.*

class SendMailSpec extends IntegrationSpec {

	def mailService
	def springSecurityService
	def grailsApplication

	void "testSendMail"() {
		when:
			def mailSender = grailsApplication.mainContext.mailSender

			mailSender.setHost("smtp.gmail.com")
			mailSender.setPort(465)
			mailSender.setUsername("julien.rott")
			
			def encoded = "".bytes.encodeBase64().toString()
			def decoded = new String("".decodeBase64())
			mailSender.setPassword(decoded)
			
			def props = new Properties()
			props.putAll(Eval.me("""
				 ["mail.smtp.auth":"true",
				  "mail.smtp.socketFactory.port":"465",
				  "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
				  "mail.smtp.socketFactory.fallback":"false"]
			"""))
			mailSender.setJavaMailProperties(props)
			
			User user = new User(username: "julien.rott@gmail.com", password: "password")
			
			mailService.sendMail {
				to "julien.rott@gmail.com"
				subject "hello world"
				body (view: "/shared/mailCreationCompte", model: [user: user])
			}

		then:
			println "bkabka"
	}

}