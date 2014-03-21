class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"home", action: 'index')
        "500"(view:'/error')

        "/infos/$id"(controller: "pageInfo", action: "show")
        "/$categorieProduit"(controller: "produit", action: "list")
	}
}
