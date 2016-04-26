package com.herokuapp.rangout

class Role {

    String authority

    static constraints = {
        authority nullable: false, blank: false, unique: true
    }

    static mapping = {
        cache true
        version false
    }
}
