package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.*

@Transactional(readOnly = true)
@Secured(['isFullyAuthenticated()'])
class EstablishmentController {

    static allowedMethods = [list: "GET", getOrders: "GET", getMenu: 'GET']

    @Secured(["permitAll"])
    def list() {
        response.status = 200
        JSON.use('estList') {
            render(contentType: 'application/json') {[
                    establishments: Establishment.listOrderByName(),
                    status: 'ok'
            ]}
        }
    }

    @Secured(["EMPLOYEE_ROLE"])
    def getOrders() {
        def establishment = Establishment.findById(params?.long('establishmentId', 0))

        if (establishment == null) {
            def statusResponse = [
                    message: 'Establishment NOT FOUND',
                    status : 'error'
            ]
            return Api.error(this, 404, statusResponse)
        }
        response.status = 200
        JSON.use('ordList') {
            render(contentType: 'application/json') {[
                    orders: Order.findAllByEstablishmentAndClosed(establishment, false),
                    status: 'ok'
            ]}
        }
    }

    @Secured(["EMPLOYEE_ROLE"])
    def getMenu() {
        def establishment = Establishment.findById(params?.long('establishmentId', 0))

        if (establishment == null) {
            def statusResponse = [
                    message: 'Establishment NOT FOUND',
                    status : 'error'
            ]
            return Api.error(this, 404, statusResponse)
        }
        response.status = 200
        JSON.use('menList') {
            render(contentType: 'application/json') {[
                    menu: establishment.menu,
                    status: 'ok'
            ]}
        }
    }
}