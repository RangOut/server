package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['isFullyAuthenticated()'])
class ApplicationController {

    static allowedMethods = [index: "GET", register: "POST"]

    def grailsApplication

    @Secured(["permitAll"])
    def index() {
        def statusResponse = [
                'api' : [
                        'app'        : grailsApplication.metadata['app.name'],
                        'version'    : grailsApplication.metadata['app.version'],
                        'build'      : grailsApplication.metadata['app.buildId'],
                        'lastUpdated': grailsApplication.metadata['app.buildDate']
                ],
                'status' : "up"
        ]
        Api.ok(this, statusResponse)
    }

    @Secured(["permitAll"])
    def register() {
        def object = request.JSON
        def establishment = new Establishment(object['establishment'])

        establishment.menu?.each { item ->
            item.establishment = establishment
        }
        establishment?.tables.each { table ->
            table.establisment = establishment
        }

        def menu = establishment.menu
        def manager = establishment.manager

        manager.validate()
        if (manager.hasErrors()) {
            def errors = manager.errors
        }

        menu?.each { item ->
            item.validate()
            if (item.hasErrors()) {
                def errors = item.errors
            }
        }

        establishment.validate()
        if (establishment.hasErrors()) {
            def errors = establishment.errors
        }

//        establishment.validate()
//        if(establishment.hasErrors()) {
//            def errors = establishment.errors
//            if('nullable' == errors?.getFieldError('name')?.code) {
//                return Api.missingParameter(this, 'To create a establishment the field name is required', '[name]')
//            } else if('nullable' == errors?.getFieldError('nickname')?.code) {
//                return Api.missingParameter(this, 'To create a establishment the field nickname is required', '[nickname]')
//            } else if('unique' == errors?.getFieldError('nickname')?.code) {
//                return Api.resourceUsed(this, 'This nickname is already being used. Please choose another nickname.')
//            } else if('unique' == errors?.getFieldError('cnpj')?.code) {
//                return Api.resourceUsed(this, 'This cnpj is already being used. Please choose another cnpj.')
//            } else if('matches.invalid' == errors?.getFieldError('cnpj')?.code) {
//                return Api.incorrectFormat(this, 'cnpj')
//            } else if('matches.invalid' == errors?.getFieldError('address.cep')?.code) {
//                return Api.incorrectFormat(this, 'cep')
//            } else if('matches.invalid' == errors?.getFieldError('address.number')?.code) {
//                return Api.incorrectFormat(this, 'number')
//            } else {
//                return Api.Unexpected(this)
//            }
//        }

//        manager.validate()
//        if(manager.hasErrors()) {
//            def errors = manager.errors
//            if('unique' == errors?.getFieldError('email')?.code) {
//                return Api.resourceUsed(this, 'This email is already being used. Please choose another email.')
//            } else if('email.invalid' == errors.getFieldError('email')?.code) {
//                return Api.incorrectFormat(this, 'email')
//            } else if('matches.invalid' == errors?.getFieldError('password')?.code) {
//                return Api.incorrectFormat(this, 'password', 'Password must be more than 5 chars and include at least one numeric digit.')
//            } else if('matches.invalid' == errors?.getFieldError('username')?.code) {
//                return Api.incorrectFormat(this, 'username',
//                        'Username must be more than 2 chars, the first character cannot be a number, only one special char ' +
//                        'allowed and it must not be at the extrema of the string')
//            } else if('nullable' == errors?.getFieldError('name')?.code) {
//                return Api.missingParameter(this, 'To create a establishment manager the field name is required', '[name]')
//            } else if('nullable' == errors?.getFieldError('email')?.code) {
//                return Api.missingParameter(this, 'To create a establishment manager the field email is required', '[email]')
//            } else if('nullable' == errors?.getFieldError('username')?.code) {
//                return Api.missingParameter(this, 'To create a establishment manager the field username is required', '[username]')
//            } else if('nullable' == errors?.getFieldError('password')?.code) {
//                return Api.missingParameter(this, 'To create a establishment manager the field password is required', '[password]')
//            } else {
//                return Api.Unexpected(this)
//            }
//        }
        establishment.save(failOnError: true)

        def managerRole = new EmployeeRole(employee: manager, role: Role.findByAuthority('ROLE_MANAGER'))
        managerRole.save(flush: true, insert: true, failOnError: true)

        def employeeRole = new EmployeeRole(employee: manager, role: Role.findByAuthority('ROLE_EMPLOYEE'))
        employeeRole.save(flush: true, insert: true, failOnError: true)

        def message = "Establishment " + establishment.name + " registered with success under nickname: " + establishment.nickname
        JSON.use('estSave') {
            render(status: 201, contentType: 'application/json') {[
                    establishment: establishment,
                    message: message,
                    status : 'ok'
            ]}
        }
    }
}
