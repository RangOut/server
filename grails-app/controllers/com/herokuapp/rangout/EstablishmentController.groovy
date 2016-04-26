package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.*

@Transactional(readOnly = true)
@Secured(['isFullyAuthenticated()'])
class EstablishmentController {

    static allowedMethods = [list: "GET"]

    @Secured(["permitAll"])
    def list() {
        response.status = 200
        JSON.use('establishmentList') {
            render(contentType: 'application/json') {[
                    establishments: Establishment.listOrderByName(),
                    status: 'ok'
            ]}
        }
    }
}
