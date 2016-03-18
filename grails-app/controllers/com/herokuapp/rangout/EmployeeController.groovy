package com.herokuapp.rangout

import grails.rest.RestfulController
import grails.transaction.*

import grails.converters.JSON

@Transactional(readOnly = true)
class EmployeeController extends RestfulController<Employee> {

    static allowedMethods = [save: "POST", list: "GET"]

    EmployeeController() {
        super(Employee)
    }

    def list() {
    }

    def save() {
        String name = params?.name
        String username = params?.username
        String password = params?.password
        String organization = params?.establishment

        Establishment establishment = organization.matches("^\\d+\$") ?
                Establishment.findById(Long.parseLong(organization)) :
                Establishment.findByName(organization)
        def employee =
                new Employee(name: name, username: username, password: password, establishment: establishment)

        if(employee.validate()) {
            employee.save()

            response.status = 201
            render employee as JSON
        } else {
            response.status = 400
            render([status: "error"] as JSON)
        }
    }
}
