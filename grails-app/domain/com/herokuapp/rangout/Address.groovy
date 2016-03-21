package com.herokuapp.rangout

import groovy.transform.*

@EqualsAndHashCode(excludes = ['complement', 'id'])
class Address {

    String street
    String number
    String city
    String cep
    String state
    String country
    String complement

    static belongsTo = [establishment: Establishment]

    static constraints = {
        street nullable: false
        number nullable: false, matches: "(^N\\/A\$)|^\\d+([-\\s]?[A-Z])*\$"
        city nullable: false
        cep matches: "^[0-9]{5}([-/]?[0-9]{3,4})?\$"
        state nullable: false
        country nullable: false
    }

    static mapping = {
        table 'address'
        version false
        street column: "street"
        number column: "number"
        city column: "city"
        cep column: "cep"
        state column: "state"
        country column: "country"
        complement column: "complement"
    }
}
