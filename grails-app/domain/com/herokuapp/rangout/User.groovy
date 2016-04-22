package com.herokuapp.rangout

import grails.rest.*

@Resource(uri='api/users', readOnly=false, formats=['json'])
class User {

    String oAuth
    static hasMany = [orders: Order]

    static constraints = {
        oAuth unique: true, nullable: false
    }

    static mapping = {
        table 'usr'
        version false
        oAuth column: 'auth_agent'
    }
}
