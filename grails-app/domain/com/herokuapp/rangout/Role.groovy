package com.herokuapp.rangout

class Role {

    String authority

    static constraints = {
        authority blank: false, unique: true
    }

    static mapping = {
        cache true
        version false
    }
}
