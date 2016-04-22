package com.herokuapp.rangout

class Order {

    Double total
    boolean closed = false
    boolean attended = false

    Set<Item> items = new HashSet<>()

    static hasMany   = [items: Item]
    static belongsTo = [user: User]

    static constraints = {
        total nullable: false, blank: false, validator: {value -> return value > 0}
        items validator: {value -> return !value.isEmpty()}
    }

    static mapping = {
        table 'ordered'
        version false
        total column: 'total'
        closed column: 'closed'
        attended column: 'attended'
        items joinTable: [
                name  : 'ordered_item',
                key   : 'ordered_id',
                column: 'item_id',
                type  : long
        ], cascade: 'all-delete-orphan'
    }
}
