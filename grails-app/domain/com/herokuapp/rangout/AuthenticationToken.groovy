package com.herokuapp.rangout

class AuthenticationToken {

    String username
    String tokenValue
    Date refreshed = new Date()

    static constraints = {
        tokenValue blank: false
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
