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
        // filteringCodecForContentType.'text/html' = 'html'
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

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "https://rangout.herokuapp.com/"
    }
}

// log4j configuration
log4j.main = {
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
    info   'org.springframework.security'
    debug  'grails.app.controllers',
           'grails.app.conf',
           'grails.app.domain',
           'grails.app.services',
            'grails.app.jobs'
    error  'grails.plugin.heroku',
           'grails.plugin.cloudsupport'
}

// Heroku plugin installs database session unless excluded in BuildConfig
grails.plugin.databasesession.enabled = false

// Disable Sitemesh, no views in an API app
// Also set in sitemesh.xml
// see: https://jira.grails.org/browse/GRAILS-5770
grails.views.gsp.sitemesh.preprocess = false

// Change a context project
// For example: change http://localhost:8080/Rangout to http://localhost:8080/
grails.app.context = "/"

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.herokuapp.rangout.Employee'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.herokuapp.rangout.EmployeeRole'
grails.plugin.springsecurity.authority.className = 'com.herokuapp.rangout.Role'
grails.plugin.springsecurity.securityConfigType  = 'Annotation'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        '/':                              ['permitAll'],
        '/index':                         ['permitAll'],
        '/index.gsp':                     ['permitAll'],
        '/**/js/**':                      ['permitAll'],
        '/**/css/**':                     ['permitAll'],
        '/**/images/**':                  ['permitAll'],
        '/**/fonts/**':                   ['permitAll'],
        '/**/favicon.ico':                ['permitAll'],
        '/api/register':                  ['permitAll'],
        '/api/login/':                    ['permitAll'],
        '/api/validate':                  ['permitAll'],
        '/api/status':                    ['permitAll'],
        '/dbconsole*/**':                 ['permitAll'],
        '/login/auth':                    ['denyAll'],
        '/api/**':                        ['isFullyAuthenticated()']]

grails.plugin.springsecurity.password.algorithm = 'bcrypt'

// Pessimistic lock-down: reject all urls with no security definition
// Lock everything down by default, return 403
grails.plugin.springsecurity.rejectIfNoRule=true
grails.plugin.springsecurity.fii.rejectPublicInvocations=false

//login
grails.plugin.springsecurity.rest.login.active=true
grails.plugin.springsecurity.rest.login.endpointUrl='/api/login'
grails.plugin.springsecurity.rest.login.failureStatusCode=401

//TODO: enable me, it's more RESTFUL
grails.plugin.springsecurity.rest.login.useJsonCredentials=true
grails.plugin.springsecurity.rest.login.useRequestParamsCredentials=false
grails.plugin.springsecurity.rest.login.usernamePropertyName='username'
grails.plugin.springsecurity.rest.login.passwordPropertyName='password'

//logout
grails.plugin.springsecurity.rest.logout.endpointUrl='/api/logout'

//token generation
grails.plugin.springsecurity.rest.token.generation.useSecureRandom=true
grails.plugin.springsecurity.rest.token.generation.useUUID=false

//use Memcached
grails.plugin.springsecurity.rest.token.storage.useMemcached=false
grails.plugin.springsecurity.rest.token.storage.memcached.hosts='localhost:11211'
grails.plugin.springsecurity.rest.token.storage.memcached.username=''
grails.plugin.springsecurity.rest.token.storage.memcached.password=''
grails.plugin.springsecurity.rest.token.storage.memcached.expiration=3600

//use GROM
grails.plugin.springsecurity.rest.token.storage.useGorm=true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName='com.herokuapp.rangout.AuthenticationToken'
grails.plugin.springsecurity.rest.token.storage.gorm.tokenValuePropertyName='tokenValue'
grails.plugin.springsecurity.rest.token.storage.gorm.usernamePropertyName='username'

grails.plugin.springsecurity.rest.token.storage.useGrailsCache=false
grails.plugin.springsecurity.rest.token.storage.grailsCacheName=null

//token rendering
grails.plugin.springsecurity.rest.token.rendering.usernamePropertyName='username'
grails.plugin.springsecurity.rest.token.rendering.authoritiesPropertyName='roles'
grails.plugin.springsecurity.rest.token.rendering.tokenPropertyName='token'

//token validate
grails.plugin.springsecurity.rest.token.validation.useBearerToken=false
grails.plugin.springsecurity.rest.token.validation.headerName='X-Auth-Token'
grails.plugin.springsecurity.rest.token.validation.endpointUrl='/api/validate'

cors.enabled=true
cors.url.pattern = '/api/*'
cors.headers=[
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Credentials': true,
        'Access-Control-Allow-Headers': 'origin, authorization, accept, content-type, x-requested-with,X-Auth-Token',
        'Access-Control-Allow-Methods': 'GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS',
        'Access-Control-Max-Age': 3600
]

// Grails Spring Security REST Plugin Configuration
// http://alvarosanchez.github.io/grails-spring-security-rest/docs/guide/single.html#grailsCache
grails.plugin.springsecurity.filterChain.chainMap = [
        '/api/**': 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter',  // Stateless chain
        '/**': 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'                                                                          // Traditional chain
]


// Examples of providers: 'daoAuthenticationProvider','anonymousAuthenticationProvider','rememberMeAuthenticationProvider'
grails.plugin.springsecurity.providerNames = [
        'customAuthenticationProvider'
]