package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ProduitController {

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def list() {
    	log.debug params
    	def produits = Produit.findAllByCategorie(Categorie.findByTitre(params.categorieProduit))
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
		if (params.id > 0) {
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

}
