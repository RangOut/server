package com.herokuapp.rangout

import grails.transaction.*
import grails.converters.JSON
import grails.rest.RestfulController

@Transactional(readOnly = true)
class UserController extends RestfulController<User> {

    static allowedMethods = [save: "POST", list: "GET"]

    UserController() {
        super(User)
    }

    def list() {
    }

    /**
     *
     *
     * @param oAuth
     * */
    def save() {
        // Null-safe access: if params.oAuth is a null value its will be null instead of NullPointerException
        String oAuth = params?.oAuth
        def usr = new User(oAuth: oAuth)

        if(usr.validate()) {
            usr.save()

            response.status = 201
            render usr as JSON
        } else {
            response.status = 400
            render([status: "error", "message": "Property oAuth must be unique and cannot be null"] as JSON)
        }
    }
}
