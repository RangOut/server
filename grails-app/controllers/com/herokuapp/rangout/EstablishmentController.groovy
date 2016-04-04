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
        String nickname = params?.nickname

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

        def address = new Address(cep: cep, street: street, number: number, neighborhood: neighborhood,
                                  city: city, state: state, country: country, complement: complement)

        def establishment = new Establishment(name: name, cnpj: cmpj, nickname: nickname, address: address,
                                              telephones: telephones, cellphones: cellphones)

        String message
        if(establishment.validate()) {
            establishment.save()
            response.status = 201
            message = "Establishment " + establishment.name +
                      " registered with success under nickname: " + establishment.nickname
        } else {
            response.status = 400

            def errors = establishment.errors.allErrors.collect{g.message([error: it])}
            message = errors[0]
        }
        JSON.use("establishmentSave") {
            render(contentType: 'application/json') {[
                    establishment: establishment.hasErrors() ?   []    : establishment,
                    status:        establishment.hasErrors() ? "error" : "OK",
                    message:       message
            ]}
        }
    }
}
