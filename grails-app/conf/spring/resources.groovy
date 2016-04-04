import com.herokuapp.rangout.Employee
import com.herokuapp.rangout.Establishment
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

    establishmentSaveRenderer(JsonRenderer, Establishment) {
        namedConfiguration = 'establishmentSave'
    }
}
