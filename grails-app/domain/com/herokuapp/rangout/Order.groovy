package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class Order {

    @JsonApi(['ordList'])
    Double total = 0
    @JsonApi(['ordList'])
    Establishment establishment

    @JsonApi(['ordList'])
    boolean closed = false
    @JsonApi(['ordList'])
    Set<OrderItem> items = new HashSet<>()

    static hasMany = [items: OrderItem]
    static hasOne  = [establishment: Establishment]

    static belongsTo = [user: User]

    static constraints = {
        total nullable: false, blank: false, validator: {value -> return value > 0}
        items validator: {value -> return !value.isEmpty()}

        establishment nullable: false, blank: false
    }

    static mapping = {
        table 'ordered'
        version false
        total column: 'total'
        closed column: 'closed'
        establishment column: 'est_id'
    }

    def beforeValidate() {
        items.each { orderItem ->
            total += orderItem.item.price
        }
    }
}
