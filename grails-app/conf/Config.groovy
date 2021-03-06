// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

//redirect to https
/*grails.plugin.springsecurity.secureChannel.useHeaderCheckChannelSecurity = true
grails.plugin.springsecurity.portMapper.httpPort = 80
grails.plugin.springsecurity.portMapper.httpsPort = 443
grails.plugin.springsecurity.secureChannel.secureHeaderName = 'X-Forwarded-Proto'
grails.plugin.springsecurity.secureChannel.secureHeaderValue = 'http'
grails.plugin.springsecurity.secureChannel.insecureHeaderName = 'X-Forwarded-Proto'
grails.plugin.springsecurity.secureChannel.insecureHeaderValue = 'https'*/
grails.plugin.springsecurity.secureChannel.definition = [
   '/**':         'REQUIRES_SECURE_CHANNEL'
]
environments {
    development {
        grails.plugin.springsecurity.portMapper.httpPort = 8080
        grails.plugin.springsecurity.portMapper.httpsPort = 8443
    }
    production {
        grails.plugin.springsecurity.portMapper.httpPort = 80
        grails.plugin.springsecurity.portMapper.httpsPort = 443
    }
}

environments {
    development {
        grails.logging.jul.usebridge = true
		grails.serverURL = "https://roje.hd.free.fr/shrimps"
        paypal.url = "https://www.sandbox.paypal.com/cgi-bin/webscr"
        paypal.receiver = "julien.rott-facilitator@gmail.com"
        paypal.notify.url = "panier/paypalresponse"
        paypal.return.url = "panier/merci"
        stripe.secret.key = "sk_test_78JCt1RpARlMxcn2xVxgIgys"
        stripe.publishable.key = "pk_test_cE23iiOItXxTywyKn8qCFOTl"
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
        paypal.url = "https://www.paypal.com/cgi-bin/webscr"
        paypal.receiver = "shrimpsforall@outlook.fr"
        paypal.notify.url = "panier/paypalresponse"
        paypal.return.url = "panier/merci"
        stripe.secret.key = "sk_live_UNeySAMN719QSZojFupvxJgC"
        stripe.publishable.key = "pk_live_zvPG92zKcw81lX0z56woMamM"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

   environments {
        development {
            debug   'grails.app.controllers.fr.shrimpsforall',
                    'grails.app.services.fr.shrimpsforall',
                    'grails.app.domain'
            error   'grails.app.jobs',
                    'grails.app.filters'
        }
        test {
            debug   'grails.app.controllers.fr.shrimpsforall',
                    'grails.app.services.fr.shrimpsforall',
					'grails.app.fr.shrimpsforall',
                    'grails.app.domain'
            error   'grails.app.jobs',
                    'grails.app.filters'
        }
  }
}

//dbm config
//grails.plugin.databasemigration.reports.updateOntart = true
//grails.plugin.databasemigration.reports.changelogFileName = changelog.groovy
grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']
environments {
    test {
        grails.plugin.databasemigration.updateOnStart = false
    }
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.shrimpsforall.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.shrimpsforall.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.shrimpsforall.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
    '/index':                         ['permitAll'],
	'/infos/**':                      ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
    '/**/favicon.ico':                ['permitAll'],
    '/ajaxUpload/upload':             ['ROLE_ADMIN'],
    '/console/**':                    ['ROLE_ADMIN']
]

