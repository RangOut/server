package com.herokuapp.rangout

import grails.rest.*
import java.security.MessageDigest

@Resource(uri='/employees', readOnly=false, formats=['json'])
class Employee {

    String name
    String username
    String password

    transient securityService
    transient springSecurityService

    static belongsTo = [establishment: Establishment]
    static transients = ['securityService', 'springSecurityService']

    static constraints = {
        name nullable: false
        establishment nullable: false
        password nullable: false, matches: "^(?=.*\\d).{6,}\$"
        username nullable: false, matches: "(?=^.{3,}\$)^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+\$"
    }

    static mapping = {
        tablePerHierarchy false
        table 'employees'
        version false
        username column: 'username'
        password column: 'password'
        establishment column: 'est_id'
    }

    Set<Role> getAuthorities() {
        EmployeeRole.findAllByEmployee(this).collect { it.role }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if(isDirty('password')) encodePassword()
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
