class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"home", action: 'index')
        "500"(view:'/error')

        "/$categorieProduit"(controller: "produit", action: "list")
        "/infos/$pageInfo"(controller: "pageInfo", action: "show")
	}
}
