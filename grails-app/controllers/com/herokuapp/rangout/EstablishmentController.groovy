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
        String cmpj = params?.cmpj

        // Establishment Address attributes
        String cep = params?.cep
        String street = params?.street
        String number = params?.number
        String neighborhood = params?.neighborhood
        String city = params?.city
        String state = params?.state
        String country = params?.country
        String complement = params?.complement

        // Establishment Contact attributes
        Set<String> telephones = new HashSet<>(params?.list('telephone'))
        Set<String> cellphones = new HashSet<>(params?.list('cellphone'))

        def establishment = new Establishment(name: name, cmpj: cmpj, address: new Address(cep: cep, street: street, number: number, neighborhood: neighborhood,
                city: city, state: state, country: country, complement: complement), telephones: telephones, cellphones: cellphones)

        establishment.save(flush: true)
        response.status = 201
        render establishment as JSON
    }
}
