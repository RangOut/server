package com.herokuapp.rangout

import grails.transaction.*
import grails.converters.JSON
import grails.rest.RestfulController

import org.springframework.dao.DataIntegrityViolationException

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
        def u = new User(oAuth: oAuth)

        try {
            u.save(flush: true)

            render u as JSON
            response.setStatus(201)
            response.setCharacterEncoding("UTF-8")
            response.setContentType("application/json")
        } catch(DataIntegrityViolationException e) {

        }
    }
}
