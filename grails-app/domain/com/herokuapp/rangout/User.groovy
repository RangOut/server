package com.herokuapp.rangout

import grails.rest.*

@Resource(uri='api/users', readOnly=false, formats=['json'])
class User {

    String oAuth

    static constraints = {
        oAuth unique: true, nullable: false
    }
}
