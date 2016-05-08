package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class Table {

    @JsonApi(['selTable'])
    int number

    Table currentClient
    boolean isFree = true
    Set<UserTable> clients = new HashSet<>()

    static hasMany   = [clients: UserTable]
    static belongsTo = [establisment: Establishment]

    static constraints = {
        number nullable: false, blank: false

        clients nullable: true
        establisment  nullable: true
        currentClient nullable: true
    }

    static mapping = {
        table 'table'
        version false
        number column: 'number'
        establisment column: 'est_id'
    }

    def beforeUpdate() {
        if (isDirty('currentClient')) {
            if (currentClient == null) {
                isFree = true
            } else {
                isFree = false
            }
        }
    }
}
