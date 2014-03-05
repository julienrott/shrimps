package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class FraisPortController {

    def index() {
    	[fraisPort: FraisPort.list()]
    }

    def save() {
    	log.debug params
    	def id = params.id as long
    	def fdp

    	if (id > 0) {
    		fdp = FraisPort.get(id)
    		fdp.properties = params
    	}
    	else {
    		fdp = new FraisPort(titre: params.titre, type: params.type)
    	}

    	def msg = ""
    	if (!fdp.save()) {
    		fdp.errors.each {
    			msg += it
    		}
    		flash.message = msg
    	}

    	redirect action: 'index'
    }

    def delete() {
    	def fdp = FraisPort.get(params.id)
    	fdp.delete()
    	redirect action: 'index'
    }

    def savePlage() {
    	def fdp = FraisPort.get(params.idfdp)
    	def id = params.id as long
    	def plage

    	if (id > 0) {
    		plage = PlageFraisPort.get(id)
    		plage.properties = params
    	}
    	else {
    		plage = new PlageFraisPort(debut: params.debut, fin: params.fin, prix: params.prix)
    	}

    	fdp.addToPlages(plage)

    	def msg = ""
    	if (!plage.save()) {
    		plage.errors.each {
    			msg += it
    		}
    		flash.message = msg
    	}

    	redirect action: 'index'
    }

    def deletePlage() {
    	def plage = PlageFraisPort.get(params.id)
    	plage.delete()
    	redirect action: 'index'
    }

}
