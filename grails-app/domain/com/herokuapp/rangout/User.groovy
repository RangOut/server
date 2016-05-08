package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi
import grails.rest.Resource

@Resource(uri='/api/user', readOnly=false, formats=['json'])
class User {

    @JsonApi(['usrSave'])
    String name
    @JsonApi(['usrSave'])
    String socialId
    @JsonApi(['usrSave'])
    String username
    @JsonApi(['usrSave'])
    String email
    @JsonApi(['usrSave'])
    byte[] picture

    Set<Order> orders = new HashSet<>()
    Set<UserTable> tables = new HashSet<>()

    static hasOne  = [currentTable: UserTable]
    static hasMany = [orders: Order, tables: UserTable]

    static constraints = {
        name nullable: false, blank: false
        socialId nullable: false, blank: false, unique: true
        username nullable: true,  unique: true, matches: "(?=^.{3,}\$)^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+\$"
        email    nullable: true,  unique: true, email: true
        picture  nullable: true

        tables nullable: true
        orders nullable: true
        currentTable nullable: true
    }

    static mapping = {
        table 'user'
        version false
        name column: 'name'
        socialId column: 'socialId'
        username column: 'username'
        email column: 'email'
        picture column: 'picture'
    }
}
