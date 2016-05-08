package com.herokuapp.rangout

class UserTable {

    Set<Order> orders = new HashSet<>()

    static hasOne  = [user: User, table: Table]
    static hasMany = [orders: Order]

    static constraints = {
        user  nullable: false, blank: false
        table nullable: false, blank: false
    }

    static mapping = {
        table 'user_table'
        version false
    }

    def getAccount() {
        def account = 0
        orders?.each { order ->
            account += order.closed ? 0 : order.total
        }

        return account
    }
}
