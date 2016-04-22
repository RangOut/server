package com.herokuapp.rangout

import grails.rest.Resource

@Resource(uri='/managers', readOnly=false, formats=['json'])
class Manager extends Employee {

    String email

    static constraints = {
        establishment nullable: true
        email unique: true, nullable: false, email: true
    }

    static mapping = {
        table 'manager'
        email column: 'email'
    }
}
