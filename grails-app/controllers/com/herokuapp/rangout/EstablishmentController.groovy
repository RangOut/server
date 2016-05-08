package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.*

@Transactional(readOnly = true)
@Secured(['isFullyAuthenticated()'])
class EstablishmentController {

    def springSecurityService

    static allowedMethods = [list: "GET", getOrders: "GET", getMenu: 'GET']

    @Secured(["permitAll"])
    def list() {
        JSON.use('estList') {
            render(status: 200, contentType: 'application/json') {[
                    establishments: Establishment.listOrderByName(),
                    status: 'ok'
            ]}
        }
    }

    @Secured(["ROLE_EMPLOYEE"])
    def getOrders() {
        Employee employee = springSecurityService.currentUser
//        def establishment = Establishment.findById(params?.long('establishmentId', 0))
//
//        if (establishment == null) {
//            def         statusResponse = [
//                    message: 'Establishment NOT FOUND',
//                    status : 'error'
//            ]
//            return Api.error(this, 404, statusResponse)
//        }

        JSON.use('ordList') {
            render(status: 200, contentType: 'application/json') {[
                    orders: [],
                    status: 'ok'
            ]}
        }
    }

    @Secured(["ROLE_EMPLOYEE"])
    def getMenu() {
        Employee employee = springSecurityService.currentUser
//        def establishment = Establishment.findById(params?.long('establishmentId', 0))
//
//        if (establishment == null) {
//            def statusResponse = [
//                    message: 'Establishment NOT FOUND',
//                           status : 'error'
//            ]
//            return Api.error(this, 404, statusResponse)
//        }
        JSON.use('menList') {
            render(status: 200, contentType: 'application/json') {[
                    menu: employee.establishment.menu,
                    status: 'ok'
            ]}
        }
    }
}