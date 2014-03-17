package fr.shrimpsforall

import grails.test.mixin.TestFor
import grails.test.mixin.webflow.WebFlowUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestMixin(WebFlowUnitTestMixin)
@TestFor(HomeController)
class HomeControllerSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	def "test create account flow"() {

		when: "user loads page with registration form"
			println controller.newAccountFlow.properties
			newAccountFlow.createNewAccount.action()

		then: "empty userCommand object is created and lastEvent is start"
			//lastEventName == 'showCreateAccount'
			//flow.userCommand instanceof UserCommand

		/*when: "user submits the form with empty lines"
			registerFlow.createAccount.action()

		then: "userCommand with errors is returned"
			flow.user.hasErrors()
			lastEventName == 'showCreateAccount'
			lastTransitionName == 'showCreateAccount'*/
	}

}
