package com.herokuapp.rangout

import groovy.transform.*

@EqualsAndHashCode(excludes = ['id'])
class Contact {

    static belongsTo = [establishment: Establishment]
    static hasMany = [telephones: String, cellphones: String]

    static constraints = {
        telephones unique: true, nullable: false, matches:"^(\\d{2,3}|\\(\\d{2,3}\\))?[ ]?\\d{3,4}[-]?\\d{4}\$"
        cellphones unique: true
    }

    static mapping = {
        table 'contact'
        version false
        telephones joinTable: [name: 'contact', key:'id', column: 'telephone', type: "text"]
        cellphones joinTable: [name: 'contact', key:'id', column: 'cellphone', type: "text"]
    }
}
