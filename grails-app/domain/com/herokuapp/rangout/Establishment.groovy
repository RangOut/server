package com.herokuapp.rangout

import grails.plugins.jsonapis.JsonApi
import grails.rest.Resource
import grails.validation.Validateable
import groovy.transform.EqualsAndHashCode

@Validateable
@Resource(uri='api/establishment', readOnly=false, formats=['json'])
class Establishment {

    @JsonApi(['estSave', 'estList', 'selTable'])
    String name
    @JsonApi(['estSave'])
    String cnpj
    @JsonApi(['estSave'])
    String nickname
    @JsonApi(['estSave', 'estList'])
    Address address
    @JsonApi(['estSave', 'empList'])
    Manager manager

    @JsonApi(['estSave', 'menList'])
    Set<Item> menu = new HashSet<>()
    @JsonApi(['estSave', 'estList'])
    Set<Table> tables = new HashSet<>()
    @JsonApi(['empList'])
    Set<Employee> employees = new HashSet<>()

    @JsonApi(['estSave', 'estList'])
    Set<String> telephones = new HashSet<>()
    @JsonApi(['estSave'])
    Set<String> cellphones = new HashSet<>()

    static hasMany  = [employees: Employee, menu: Item, tables: Table, telephones: String, cellphones: String]
    static embedded = ['address']

    static constraints = {
        name nullable: false, blank: false
        cnpj nullable:  true,  blank: false, unique: true, matches: "^(\\d{2}.?\\d{3}.?\\d{3}\\/?\\d{4}-?\\d{2})\$"

        manager    nullable: true
        nickname   nullable: false, blank: false, unique: true
        telephones nullable: false, blank: false, validator: { value -> return !value.isEmpty() }
        cellphones nullable: true

        address nullable: false, blank:false, validator: { value, object, errors ->
            object.list()?.each { objEst ->
                if (value.id != objEst.address.id && value.equals(objEst.address))
                    errors.reject('address', 'unique')
            }
        }
        menu   nullable: false, blank: false, validator: { value -> return !value.isEmpty() }
        tables nullable: false, blank: false, validator: { value -> return !value.isEmpty() }
    }

    static mapping = {
        table 'establishment'
        version false
        name column: 'name'
        nickname column: 'nickname'
        cnpj column: "cnpj"
        manager column: 'manager_id'
        telephones joinTable: [
                name: 'tel_establishment',
                key: 'est_id',
                column: 'telephone',
                type: 'text',
        ], cascade: 'all-delete-orphan'
        cellphones joinTable: [
                name: 'cel_establishment',
                key: 'est_id',
                column: 'cellphone',
                type: 'text'
        ], cascade: 'all-delete-orphan'
    }

    boolean hasFreeTable() {
        return tables.any{ t -> t.isFree }
    }

    Table getAFreeTable() {
        return tables.find{ t -> t.isFree }
    }
}

@EqualsAndHashCode(excludes = ['id', 'cep', 'complement'])
class Address {

    @JsonApi(['estSave', 'estList'])
    String cep
    @JsonApi(['estSave', 'estList'])
    String street
    @JsonApi(['estSave', 'estList'])
    String number
    @JsonApi(['estSave', 'estList'])
    String neighborhood
    @JsonApi(['estSave', 'estList'])
    String city
    @JsonApi(['estSave', 'estList'])
    String state
    @JsonApi(['estSave', 'estList'])
    String country
    @JsonApi(['estSave', 'estList'])
    String complement

    static constraints = {
        street       nullable: false, blank: false
        neighborhood nullable: false, blank: false
        city         nullable: false, blank: false
        state        nullable: false, blank: false
        country      nullable: false, blank: false
        complement   nullable: true,  blank: false

        cep          nullable: true,  matches: "^[0-9]{5}([-/]?[0-9]{3,4})?\$"
        number       nullable: false, blank: false, matches: "^N\\/A\$|^\\d+([-\\s]?[A-Z])*\$"
    }

    static mapping = {
        version false
        cep column: "cep"
        street column: "street"
        number column: "number"
        neighborhood column: "neighborhood"
        city column: "city"
        state column: "state"
        country column: "country"
        complement column: "complement"
    }
}
