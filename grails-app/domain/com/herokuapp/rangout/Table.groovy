package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class Table {

    @JsonApi(['selTable'])
    int number
    double account
    boolean isFree = true

    User client
    Set<Order> orders = new HashSet<>()

    static hasMany   = [orders: Order]
    static belongsTo = [establisment: Establishment]

    static constraints = {
        orders nullable: true
        client nullable: true
    }

    static mapping = {
        table 'table'
        version false
        number column: 'number'
        account column: 'account'
        establisment column: 'est_id'
        orders joinTable: [
                name: 'table_ord',
                key: 'tab_id',
                column: 'ord_id',
                type: long
        ], cascade: 'all-delete-orphan'
    }

//    def beforeUpdate() {
//        if (isDirty('client')) {
//            if (client == null) {
//                isFree = true
//            } else {
//                isFree = false
//            }
//        }
//    }
}
