package com.herokuapp.rangout

class Item {

    String name
    String description
    String category
    Double price

    static hasMany = [ingredients: String]
    static belongsTo = [establishment: Establishment]

    static constraints = {
        name nullable: false
        description nullable: true
        category nullable: true
        price nullable: false //TODO(clenimar): assert price>0
    }

    static mapping = {
        table 'item'
        name column: 'name'
        description column: 'description'
        category column: 'category'
        price column: 'price'
        ingredients joinTable: [
                name: 'item_ingredients',
                column: 'ingredient',
                type: 'text'
        ]
        cache true
        version false
    }
}
