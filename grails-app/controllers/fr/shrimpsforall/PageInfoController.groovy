package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class PageInfoController {

    def index() {
    	log.debug params
    	[pagesInfo: PageInfo.list(sort: 'position'), pageInfo: PageInfo.get(params.id)]
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show() {
    	def pageInfo = PageInfo.findByTitre(params.pageInfo)
    	[titre:pageInfo.titre, pageInfo: pageInfo]
    }

    def save() {
    	def pageInfo
    	
    	if(params.id) {
    		pageInfo = PageInfo.get(params.id)
    		pageInfo.properties = params
    	}
    	else{
    		pageInfo = new PageInfo(params)
    	}

    	if(!pageInfo.save()) {
    		def msg = ""
    		pageInfo.errors.each {
    			msg += it
    		}
    		flash.message = msg
    	}

    	redirect action: 'index', model: [pageInfo: pageInfo]
    }

    def delete() {
    	def pageInfo = PageInfo.get(params.id)
    	pageInfo.delete()
    	redirect action: 'index'
    }

}
