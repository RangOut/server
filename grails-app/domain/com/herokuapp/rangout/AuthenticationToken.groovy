package com.herokuapp.rangout

class AuthenticationToken {

    String token
    String username
    Date refreshed = new Date()

    static constraints = {
        token blank: false
        username blank: false, unique: true
    }

    static mapping = {
        version false
    }

    def afterLoad() {
        refreshed = new Date()
        this?.save()
    }
}
