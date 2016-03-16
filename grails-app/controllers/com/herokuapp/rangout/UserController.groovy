package com.herokuapp.rangout

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController extends RestfulController<User> {
    
    UserController() {
        super(User)
    }

    def list() {
        def users = User.list()
        render users as JSON
    }

    def save() {
    }
}
