package fr.shrimpsforall

import grails.test.WebFlowTestCase

class HomeControllerTests extends WebFlowTestCase {

	protected void setUp() {
    	super.setUp()

		/*registerFlow("other/otherSub") {
			// register a simplified mock 
			start {
				on("next").to("end")
			}
			end()
		}
		// register the original subflow
		registerFlow("example/sub", new ExampleController().subFlow) */
	}

	def getFlow() {
		new HomeController().newAccountFlow
	}

	void testNewAccountSubflow() {
		def showCreateAccountView = startFlow()
		signalEvent("createAccount")
		assert "showCreateAccount" == flowExecution.activeSession.state.id
	}

}