package com.herokuapp.rangout

import grails.transaction.Transactional

@Transactional
class SecurityService {
    def springSecurityService

    /**
     * Generate a strong password to be used when resetting user's pass
     */
    def generateStrongPassword() {
        def alphabet = (('A'..'Z') + ('a'..'z') + ('0'..'9')).join()
        def n = 6
        def result = new Random().with {
            (1..n - 1).collect {
                alphabet[nextInt(alphabet.length())]
            }.join()
        }
        result + new Random().nextInt(10)
    }

    /**
     * Encode password for storing in database
     */
    def encodePassword(String password) {
        def encodedPassword = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password

        log.debug("Encoded password is: " + password)
        encodedPassword
    }
}
