package com.herokuapp.rangout

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.*

import grails.converters.JSON
import org.springframework.context.support.StaticMessageSource

@Transactional(readOnly = true)
@Secured(['isFullyAuthenticated()'])
class EmployeeController {

    static allowedMethods = [save: "POST", list: "GET", delete: "DELETE"]

    @Secured(["ROLE_MANAGER"])
    def list() {
        def establishment = Establishment.findById(params?.long('establishmentId', 0))

        if (establishment == null) {
            def statusResponse = [
                    message: 'Establishment NOT FOUND',
                    status : 'error'
            ]
            return Api.error(this, 404, statusResponse)
        }
        JSON.use('empList') {
            render(status: 200, contentType: 'application/json') {[
                    employees: establishment.employees,
                    manager  : establishment.manager,
                    status: 'ok'
            ]}
        }
    }

    def show() {

    }

    @Secured(["ROLE_MANAGER"])
    def save() {
        def object = request.JSON
        def establishment = Establishment.findById(params?.long('establishmentId', 0))

        if (establishment == null) {
            def statusResponse = [
                    message: 'Establishment NOT FOUND',
                    status : 'error'
            ]
            return Api.error(this, 404, statusResponse)
        }
        def name     = object['name']
        def username = object['username']
        def password = object['password']
        def employee = new Employee(name: name, username: username, password: password, establishment: establishment)

        employee.validate()
        if (employee.hasErrors()) {
            def messageSource = new StaticMessageSource()

            def errors = employee.errors.fieldErrors.collect{
                [
                     'message': messageSource.getMessage(it, null),
                     'field': it.field,
                     'badValue': it.rejectedValue
                ]
            }
            return render(status: 400, contentType: "application/json") {
                [
                    message:'Failed to save',
                    errors: errors,
                    status: 'error'
                ]
            }
        }
        employee.save()

        def employeeRole = new EmployeeRole(employee: employee, role: Role.findByAuthority('ROLE_EMPLOYEE'))
        employeeRole.save(flush: true, insert: true, failOnError: true)

        def message = "Employee " + employee.name +" registered with success in the establishment " + establishment.name
        JSON.use("empSave") {
            render(status: 201, contentType: 'application/json') {[
                employee: employee,
                message : message,
                status  : 'ok'
            ]}
        }
    }

    @Secured(["ROLE_MANAGER"])
    def delete() {
        def establishment = Establishment.findById(params?.long('establishmentId', 0))
    }
}
