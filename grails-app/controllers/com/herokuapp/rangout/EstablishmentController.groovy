package com.herokuapp.rangout

import grails.converters.JSON

import grails.rest.RestfulController
import grails.transaction.*

@Transactional(readOnly = true)
class EstablishmentController extends RestfulController<Establishment> {

    static allowedMethods = [save: "POST", list: "GET"]

    EstablishmentController() {
        super(Establishment)
    }

    def list() { }

    def save() {
        String name = params?.name

        def establishment = new Establishment(name: name)
        establishment.save()

        response.status = 201
        render establishment as JSON
    }
}
