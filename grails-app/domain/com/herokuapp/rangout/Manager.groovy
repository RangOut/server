package com.herokuapp.rangout

import grails.rest.Resource

@Resource(uri='/managers', readOnly=false, formats=['json'])
class Manager extends Employee {

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
