import com.herokuapp.rangout.Address
import com.herokuapp.rangout.CustomAccessTokenJsonRenderer
import com.herokuapp.rangout.CustomAuthenticationProvider
import com.herokuapp.rangout.JsonPayloadCredentialsExtractorService
import com.herokuapp.rangout.Employee
import com.herokuapp.rangout.UserDetailsService
import grails.rest.render.json.JsonRenderer

import grails.plugin.springsecurity.SpringSecurityUtils

def conf = SpringSecurityUtils.securityConfig

// Place your Spring DSL code here
beans = {
    employeeRenderer(JsonRenderer, Employee) {
        excludes = ['class', 'establishment', 'password']
    }

    addressRenderer(JsonRenderer, Address) {
        excludes = ['class', 'errors', 'id', 'version']
    }

    credentialsExtractor(JsonPayloadCredentialsExtractorService)

    accessTokenJsonRenderer(CustomAccessTokenJsonRenderer)

    userDetailsService(UserDetailsService) {
        grailsApplication = ref('grailsApplication')
    }

    customAuthenticationProvider(CustomAuthenticationProvider) {
        securityService   = ref('securityService')
        customUserDetails = ref('userDetailsService')
    }
}
