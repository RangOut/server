package com.herokuapp.rangout

import grails.rest.*
import grails.validation.Validateable
import groovy.transform.EqualsAndHashCode

@Validateable
@Resource(uri='api/establishments', readOnly=false, formats=['json'])
class Establishment {

    String name
    String cnpj
    String nickname

    Address address
    Manager manager

    static hasMany  = [employees: Employee, telephones: String, cellphones: String]
    static embedded = ['address']

    static constraints = {
        name nullable: false
        nickname unique: true, nullable: false
        cnpj unique: true, nullable: true, matches: "^(\\d{2}.?\\d{3}.?\\d{3}\\/?\\d{4}-?\\d{2})\$"
        address nullable: false, validator: { value, object, errors ->
            if(value.cep != null && !(value.cep ==~ "^[0-9]{5}([-/]?[0-9]{3,4})?\$"))
                errors.rejectValue('address.cep', 'matches.invalid')
            if(!(value.number ==~ "(^N/A\$)|^[1-9]\\d*([- s]?[A-Z])*\$"))
                errors.rejectValue('address.number', 'matches.invalid')
            if(value.street == null || value.number == null || value.neighborhood == null)
                errors.rejectValue('address', 'null.attribute.address')
            if(value.city == null || value.state == null || value.country == null)
                errors.rejectValue('address', 'null.attribute.address')

            for(Establishment establishment : object.list()) {
                if(value.id != establishment.address.id && value == establishment.address) {
                    errors.rejectValue('address', 'unique')

                }
            }
        }
        manager nullable: true
        telephones nullable: false, validator: {value -> return !value.isEmpty()}
        cellphones nullable: true
    }

    static mapping = {
        table 'establishment'
        version false
        name column: 'name'
        nickname column: 'nickname'
        cnpj column: "cnpj"
        manager column: 'manager_id'
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

@EqualsAndHashCode(excludes = ['id', 'cep', 'complement'])
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
        cep nullable: true, matches: "^[0-9]{5}([-/]?[0-9]{3,4})?\$"
        street nullable: false
        number nullable: false, matches: "^N\\/A\$|^\\d+([-\\s]?[A-Z])*\$"
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
