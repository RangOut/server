package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class Table {

    @JsonApi(['selTable'])
    int number
    @JsonApi(['selTable'])
    boolean isFree = true
    Set<UserTable> clients = new HashSet<>()

    static hasMany = [clients: UserTable]

    static belongsTo = [establisment: Establishment]

    static constraints = {
        number nullable: false, blank: false

        clients nullable: true
//        establisment  nullable: true
    }

    static mapping = {
        table 'table'
        version false
        number column: 'number'
        establisment column: 'est_id'
    }
}
