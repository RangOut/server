package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi
import grails.rest.Resource

@Resource(uri='/manager', readOnly=false, formats=['json'])
class Manager extends Employee {

    @JsonApi(['estSave', 'empList'])
    String email

    static constraints = {
        email nullable: false, blank: false, unique: true, email: true

        establishment nullable: true
    }

    static mapping = {
        table 'manager'
        email column: 'email'
    }
}
