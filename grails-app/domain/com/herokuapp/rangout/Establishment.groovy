package com.herokuapp.rangout

import grails.rest.*

@Resource(uri='api/establishments', readOnly=false, formats=['json'])
class Establishment {

    String name
    String cmpj

    Address address

    static hasMany  = [employees: Employee, managers: Employee, telephones: String, cellphones: String]
    static embedded = ['address']

    static constraints = {
        name nullable: false
        cmpj unique: true, nullable: false, matches: "^(\\d{2}.?\\d{3}.?\\d{3}\\/?\\d{4}-?\\d{2})\$"
        address nullable: false
        telephones nullable: false, validator: {value -> return !value.isEmpty()}
        cellphones nullable: true
    }

    static mapping = {
        table 'establishment'
        version false
        name column: 'name'
        cmpj column: "cmpj"
        telephones joinTable: [
                name: 'tel_establishment',
                column: 'telephone',
                type: 'text',
        ]
        cellphones joinTable: [
                name: 'cel_establishment',
                column: 'cellphone',
                type: 'text'
        ]
    }
}

class Address {

    String cep
    String street
    String number
    String neighborhood
    String city
    String state
    String country
    String complement

    static constraints = {
        cep nullable: true
        street nullable: false
        number nullable: false
        neighborhood nullable: false
        city nullable: false
        state nullable: false
        country nullable: false
        complement nullable: true
    }

    static mapping = {
        version false
        cep column: "cep"
        street column: "street"
        number column: "number"
        neighborhood column: "neighborhood"
        city column: "city"
        state column: "state"
        country column: "country"
        complement column: "complement"
    }
}
