package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index() { }
}
