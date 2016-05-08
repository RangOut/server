package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class Order {

    @JsonApi(['ordList'])
    Double total = 0
    @JsonApi('ordList')
    boolean closed = false
    @JsonApi(['ordList'])
    Set<OrderItem> items = new HashSet<>()

    static hasOne  = [table: UserTable]
    static hasMany = [items: OrderItem]

    static belongsTo = [user: User]

    static constraints = {
        total nullable: false, blank: false, validator: {value -> return value > 0}
        items nullable: false, blank: false, validator: {value -> return !value.isEmpty()}

        table nullable: false, blank: false
    }

    static mapping = {
        table 'ordered'
        version false
        total column: 'total'
        closed column: 'closed'
    }

    def beforeValidate() {
        items.each { orderItem ->
            total += orderItem.item.price
        }
    }
}
