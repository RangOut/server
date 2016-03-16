package com.herokuapp.rangout

import grails.rest.*

@Resource(uri="/api/user", readOnly=false, formats=["json"])
class User {

    static constraints = {
    }
}
