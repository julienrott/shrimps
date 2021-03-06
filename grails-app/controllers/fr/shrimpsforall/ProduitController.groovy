package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ProduitController {

	def springSecurityService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def list() {
    	log.debug params
    	def isAdmin = springSecurityService.getCurrentUser()?.authorities?.authority?.contains("ROLE_ADMIN")?:false
    	log.debug isAdmin
    	def produits = Produit.findAllByCategorie(Categorie.findByTitre(params.categorieProduit))
    	def produitPhotos = Produit.findByTitre("photos")
    	if (!isAdmin && produits.contains(produitPhotos)) {
    		produits.remove(produitPhotos)
    	}
    	log.debug produits
    	[titre: params.categorieProduit, produits: produits]
    }

	def add() {
		def produit = new Produit(categorie: Categorie.findByTitre(params.categorie))
		render view: 'edit', model: [produit: produit]
	}

	def edit() {
		def produit = Produit.get(params.id)
		[produit: produit]
	}

	def delete() {
		def produit = Produit.get(params.id)
		params.categorieProduit = produit.categorie.titre
		produit.delete()
		redirect action: 'list', params: params
	}

	def save() {
    	log.debug params
		
		def produit
		
		if (params.id) {
			produit = Produit.get(params.id)
			produit.properties = params
		}
		else {
			produit = new Produit(params)
		}
		
		if(!produit.save()) {
			def msg = ""
			produit.errors.each {
				msg += it
			}
			flash.message = msg
		}
		
		render view: 'edit', model: [produit: produit]
	}

	def addLot() {
		def lot = new Lot(params)
		if (!lot.save()) {
			def msg = ""
			lot.errors.each {
				msg += it
			}
			flash.message = msg
		}
		
		redirect action: 'edit', id: lot.produit.id
	}

	def updateLot() {
		def lot = Lot.get(params.id)
		lot.properties = params
		if (!lot.save()) {
			def msg = ""
			lot.errors.each {
				msg += it
			}
			flash.message = msg
		}
		
		redirect action: 'edit', id: lot.produit.id
	}

	def deleteLot() {
		def lot = Lot.get(params.id)
		lot.delete()
		redirect action: 'edit', id: lot.produit.id
	}

}
