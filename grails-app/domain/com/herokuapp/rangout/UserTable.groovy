package com.herokuapp.rangout

class UserTable implements Serializable {

    private static final long serialVersionUID = 1

    double account
    Set<Order> orders = new HashSet<>()

    static hasOne  = [user: User, table: Table]
    static hasMany = [orders: Order]

    static constraints = {
        account nullable: false, table: false, validator: { value -> return value >= 0 }
        orders  nullable: false, blank: false, validator: { value -> !value.isEmpty() }
    }

    static mapping = {
        table 'user_table'
        version false

        id composite: ['user', 'table']
    }
}
