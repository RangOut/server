package com.herokuapp.rangout

import grails.rest.*

@Resource(uri='api/establishments', readOnly=false, formats=['json'])
class Establishment {

    String name
    String cmpj

    static hasOne = [address: Address, contact: Contact]
    static hasMany  = [employees: Employee, managers: Employee]

    static constraints = {
        name unique: true, nullable: false
        cmpj unique: true, nullable: false, matches: "^(\\d{2}.?\\d{3}.?\\d{3}\\/?\\d{4}-?\\d{2})\$"
        address unique: true, nullable: false
        contact nullable: false
    }

    static mapping = {
        table 'establishment'
        version false
        name column: 'name'
        cmpj column: "cmpj"
    }
}
