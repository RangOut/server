package com.herokuapp.rangout

import grails.rest.*

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
        name     nullable: false, blank: false
        password nullable: false, blank: false, matches: "^(?=.*\\d).{6,}\$"
        username nullable: false, blank: false, unique: true, matches: "(?=^.{3,}\$)^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+\$"

        establishment nullable: false, blank: false
    }

    static mapping = {
        tablePerHierarchy false
        table 'employee'
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
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
