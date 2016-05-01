package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi

class OrderItem implements Serializable {

    private static final long serialVersionUID = 1

    Order order

    @JsonApi(['ordList'])
    Item item
    @JsonApi(['ordList'])
    boolean attended = false

    static belongsTo = [order: Order]

    static constraints = {
        item  nullable: false, blank: false
        order nullable: false, blank: false
    }

    static mapping = {
        table 'ord_item'
        version false
        order column: 'ord_id'
        item  column: 'item_id'
        attended column: 'attended'

        id composite: ['order', 'item']
    }
}