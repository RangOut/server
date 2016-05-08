package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class Order {

    @JsonApi(['ordList'])
    Double total = 0
    boolean closed = false

    @JsonApi(['ordList'])
    Set<OrderItem> items = new HashSet<>()

    static hasOne  = [table: UserTable]
    static hasMany = [items: OrderItem]

    static belongsTo = [user: User]

    static constraints = {
        total nullable: false, blank: false
        table nullable: false, blank: false
        items nullable: false, blank: false, validator: { value -> !value.isEmpty() }
    }

    static mapping = {
        table 'ordered'
        version false
        total column: 'total'
        closed column: 'closed'
    }

    def beforeInsert() {
        total = 0
        items.each { orderItem ->
            total += orderItem.item.price * orderItem.amount
        }
    }
}
