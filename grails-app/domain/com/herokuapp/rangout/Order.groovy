package com.herokuapp.rangout

class Order {

    String customerId
    Double total

    static hasMany = [items: Item]

    static constraints = {
        customerId nullable: false
        total nullable: false, min: 0
        items nullable: false, validator: {value -> return !value.isEmpty()}
    }

    static mapping = {
        table 'order'
        customerId column: 'customerId'
        total column: 'total'
        items jonTable: [
                name: 'items'
                column: 'item_name'
                //TODO(clenimar): finish this...
        ]
        cache true
        version false
    }
}
