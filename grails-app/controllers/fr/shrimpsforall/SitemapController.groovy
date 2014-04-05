package fr.shrimpsforall

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class SitemapController {

    def index() {

		render(contentType: 'text/xml', encoding: 'UTF-8') {
			mkp.yieldUnescaped '<?xml version="1.0" encoding="UTF-8"?>'
			urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9",
					'xmlns:xsi': "http://www.w3.org/2001/XMLSchema-instance",
					'xsi:schemaLocation': "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd") {
				url {
					loc("${createLink(absolute: true, uri: '/')}")
					changefreq('weekly')
					priority(1.0)
				}
				
				Categorie.list(sort: 'position', order: 'asc').each {
    				def baseURL = "${createLink(absolute: true, uri: "/$it.titre")}"
					log.debug baseURL
					url {
						loc(baseURL.encodeAsURL())
					}
				}
				
				PageInfo.list(sort: 'position', order: 'asc').each {
    				def baseURL = "${createLink(absolute: true, uri: "/infos/$it.titre")}"
					log.debug baseURL
					url {
						loc(baseURL.encodeAsURL())
					}
				}

			}
	   }
	}
}
