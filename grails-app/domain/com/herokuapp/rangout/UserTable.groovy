package com.herokuapp.rangout

class UserTable implements Serializable {

    private static final long serialVersionUID = 1

    double account = 0
    boolean closed = false
    Set<Order> orders = new HashSet<>()

    static hasOne  = [user: User, table: Table]
    static hasMany = [orders: Order]

    static constraints = {
        account nullable: false, table: false, validator: { value -> return value > 0 }

        user  nullable: false, blank: false
        table nullable: false, blank: false
    }

    static mapping = {
        table 'user_table'
        version false
        closed column: 'closed'
    }
}
