package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class PanierController {

    def index() { }

    def vider() {
    	session.panier = null
    	render template: 'panier'
    }

    def delrow() {
        def panier = session.panier
        def produit = Produit.get(params.id)
        def iterator = panier.lignes.iterator()

        while(iterator.hasNext()) {
            def ligne = iterator.next()
            if(ligne.produit.id == params.id as long) {
                iterator.remove()
            }
        }

        redirect action: 'index'
    }

    def inc() {
        def panier = session.panier
        def produit = Produit.get(params.id)

        panier.lignes.each { ligne ->
            if(ligne.produit.id == params.id as long) {
                ligne.quantite += 1
            }
        }

        redirect action: 'index'
    }

    def dec() {
        def panier = session.panier
        def produit = Produit.get(params.id)

        panier.lignes.each { ligne ->
            if(ligne.produit.id == params.id as long) {
                if(ligne.quantite > 1) {
                    ligne.quantite -= 1
                }
            }
        }

        redirect action: 'index'
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def add() {
    	log.debug params

    	def panier = session.panier?:new Panier()
    	def produit = Produit.get(params.id)
        def quantite = params.quantite as int
    	def added = false

		panier.lignes.each { ligne ->
			if(ligne.produit.id == params.id as long) {
				ligne.quantite += quantite
				added = true
			}
    	}

    	if(!added) {
    		LignePanier ligne = new LignePanier(produit: produit, quantite: quantite)
    		if(!panier.lignes) {
	    		panier.lignes = []
    		}
    		panier.lignes.add(ligne)
    	}

		session.panier = panier

    	render template: 'panier'
    }
}
