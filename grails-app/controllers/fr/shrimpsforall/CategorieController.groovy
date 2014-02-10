package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class CategorieController {

	static scaffold = true

	def categorieService

	def index() {
		log.debug params
		[categories: Categorie.list(sort: 'position', order: 'asc'), categorie: Categorie.get(params.id)]
	}

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

	def edit() {
		log.debug params
		def categorie = Categorie.get(params.id)
		
		redirect action: "index", params: params
	}
}
