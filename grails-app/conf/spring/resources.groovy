import com.herokuapp.rangout.Address
import com.herokuapp.rangout.Employee
import com.herokuapp.rangout.CustomUserDetailsService
import grails.rest.render.json.JsonRenderer

import grails.plugin.springsecurity.SpringSecurityUtils

def conf = SpringSecurityUtils.securityConfig

// Place your Spring DSL code here
beans = {
    employeeListRenderer(JsonRenderer, Employee) {
        namedConfiguration = 'employeeList'
    }

    employeeSaveRenderer(JsonRenderer, Employee) {
        namedConfiguration = 'employeeSave'
    }

    addressRenderer(JsonRenderer, Address) {
        excludes = ['class', 'errors', 'id', 'version']
    }

    userDetailsService(CustomUserDetailsService) {
        grailsApplication = ref('grailsApplication')
    }
}
