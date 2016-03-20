package com.herokuapp.rangout

import grails.rest.*
import java.security.MessageDigest

@Resource(uri='/employees', readOnly=false, formats=['json'])
class Employee {

    String name
    /*
     * Only one special char (._-) allowed and it must not be at the extrema of the string
     * The first character cannot be a number
     * All the other characters allowed are letters and numbers
     * The total length should be more than 3 chars
     *
     **/
    String username
    // Password must be more than 6 chars and include at least one numeric digit.
    String password
    Establishment establishment

    static belongsTo = Establishment
    static hasOne = [establishment: Establishment]

    static constraints = {
        name nullable: false
        establishment nullable: false
        password matches: "^(?=.*\\d).{6,}\$", nullable: false
        username nullable: false,
                matches: "(?=^.{3,}\$)^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+\$",
                validator: {value, object ->
                    for(Employee e : object.establishment.employee) {
                        if(e.username == value) {
                            return false
                        }
                    }
                }
    }

    static mapping = {
        table 'employee'
        version false
        username column: 'user_name'
        password column: 'hash_password'
        establishment column: 'establishment_id'
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
