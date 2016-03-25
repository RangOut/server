package com.herokuapp.rangout

import grails.rest.*
import java.security.MessageDigest

@Resource(uri='/employees', readOnly=false, formats=['json'])
class Employee {

    String name
    /* Constraints:
     *
     * Only one special char (._-) allowed and it must not be at the extrema of the string
     * The first character cannot be a number
     * All the other characters allowed are letters and numbers
     * The total length should be more than 3 chars
     **/
    String username
    /* Constraints:
     *
     * Password must be more than 6 chars and include at least one numeric digit.
     **/
    String password

    static belongsTo = [establishment: Establishment]

    static constraints = {
        name nullable: false
        establishment nullable: false
        password matches: "^(?=.*\\d).{6,}\$", nullable: false
        username nullable: false, matches: "(?=^.{3,}\$)^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+\$",
                validator: {value, object ->
                    for(Employee e : object.establishment.employees) {
                        if(e.username == value) return false
                    }
                }
    }

    static mapping = {
        table 'employees'
        version false
        username column: 'username'
        password column: 'password'
        establishment column: 'est_id'
    }

    def beforeInsert() {
        password = generateSecurityPassword()
    }

    private def generateSecurityPassword() {
        MessageDigest message = MessageDigest.getInstance("MD5")
        message.update(password?.getBytes())

        byte[] digest = message.digest()
        StringBuffer buffer = new StringBuffer()

        for(byte bit : digest)
            buffer.append(String.format("%02x", bit & 0xff))

        return buffer.toString()
    }
}
