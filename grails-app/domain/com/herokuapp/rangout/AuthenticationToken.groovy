package com.herokuapp.rangout

class AuthenticationToken {

    String username
    String tokenValue

    boolean expired = false
    Date refreshed  = new Date()

    static constraints = {
        tokenValue nullable: false, blank: false
        username   nullable: false, blank: false
    }

    static mapping = {
        table 'token'
        version false
    }

    def afterLoad() {
        refreshed = new Date()
        this?.save()
    }

    def afterInsert() {
        def results = AuthenticationToken.findAllByRefreshedLessThanAndUsernameAndExpired(refreshed, username, false)

        results?.each { token ->
            AuthenticationToken.withTransaction() {
                token.setExpired(true)
                token.save()
            }
        }
    }
}
