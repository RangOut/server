package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class OrderItem {

    @JsonApi(['ordList'])
    Item item
    boolean attended = false

    @JsonApi(['ordList'])
    int amount

    static belongsTo = [order: Order]

    static constraints = {
        item   nullable: false, blank: false
        order  nullable: false, blank: false
        amount nullable: false, blank: false, validator: { value -> return value >= 1 }
    }

    static mapping = {
        table 'ord_item'
        version false
        order column: 'ord_id'
        item  column: 'item_id'
        attended column: 'attended'
        amount column: 'amount'
    }
}