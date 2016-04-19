package com.herokuapp.rangout

import grails.transaction.Transactional

@Transactional
class AuthenticationService {

    def springSecurityService

    /**
     * Get the currently logged in user
     */
    def getLoggedInUser() {
        def employee = springSecurityService.currentUser
        employee
    }
}
