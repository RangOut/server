package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = ['name', 'category'])
class Item {

    @JsonApi(['estSave', 'ordList'])
    String name
    @JsonApi(['estSave'])
    String description
    @JsonApi(['estSave', 'ordList'])
    String category
    @JsonApi(['estSave', 'ordList'])
    Double price

    boolean available = true
    @JsonApi(['estSave', 'ordList'])
    Set<String> ingredients = new HashSet<>()

    static hasMany   = [ingredients: String]
    static belongsTo = [establishment: Establishment]

    static constraints = {
        name        nullable: false, blank: false
        description nullable: true
        category    nullable: false, blank: false
        price       nullable: false, blank: false, validator: {value -> return value >= 0}
        ingredients nullable: true

        establishment nullable: true
    }

    static mapping = {
        table 'item'
        version false
        name column: 'name'
        description column: 'description'
        category column: 'category'
        price column: 'price'
        available column: 'available'
        ingredients joinTable: [
                name: 'item_ingredient',
                key: 'item_id',
                column: 'ingredient',
                type: 'text'
        ], cascade: 'all-delete-orphan'
    }
}
