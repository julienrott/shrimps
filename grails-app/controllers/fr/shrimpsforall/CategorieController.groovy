package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

class CategorieController {

	static scaffold = true

	def categorieService

    @Secured(['ROLE_ADMIN'])
	def index() {
		log.debug params
		[categories: Categorie.list(sort: 'position', order: 'asc'), categorie: Categorie.get(params.id)]
	}

	@Secured(['ROLE_ADMIN'])
	def save() {
		log.debug params

		def categorie

		if (params.id) {
			categorie = Categorie.get(params.id)
			categorie.properties = params
		}
		else {
			categorie = new Categorie(params)
		}

		if(!categorie.save()) {
			def msg = ""
			categorie.errors.each {
				msg += it
			}
			flash.message = msg
		}

		redirect action: "index", params: params
	}

	@Secured(['ROLE_ADMIN'])
	def delete() {
		log.debug params
		def categorie = Categorie.get(params.id)
		if(!categorie) {
			flash.message = "euh..."
		}
		else {
			categorie.delete()
		}
		redirect action: "index"
	}

	@Secured(['ROLE_ADMIN'])
	def edit() {
		log.debug params
		def categorie = Categorie.get(params.id)
		
		redirect action: "index", params: params
	}
}
