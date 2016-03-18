package com.herokuapp.rangout

import grails.rest.*

@Resource(uri='api/establishments', readOnly=false, formats=['json'])
class Establishment {

    String name

    static hasMany = [employee: Employee]

    static constraints = {
        name unique: true, nullable: false
    }

    static mapping = {
        table 'establishment'
        version false
        name column: 'name'
    }
}
