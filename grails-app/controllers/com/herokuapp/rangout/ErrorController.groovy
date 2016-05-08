package com.herokuapp.rangout

import grails.plugin.springsecurity.annotation.Secured

@Secured(['denyAll'])
class ErrorController {

    def forbidden() {
        def message = params?.message
        if (message == null) {
            message = 'Access to the specified resource has been forbidden.'
        }
        def statusResponse = [
                message: message,
                status : 'error'
        ]
        return Api.error(this, 403, statusResponse)
    }

    def notFound() {
        def message = params?.message
        if (message == null) {

        }
        def statusResponse = [
                message: message,
                status : 'error'
        ]
        return Api.error(this, 404, statusResponse)
    }

    def badRequest() {
        def message = params?.message
        if (message == null) {

        }
        def statusResponse = [
                message: message,
                status : 'error'
        ]
        return Api.error(this, 400, statusResponse)
    }

    def methodNotAllowed() {
        def message = params?.message
        if (message == null) {
            message = 'The specified HTTP method is not allowed for the requested resource.'
        }
        def statusResponse = [
                message: message,
                status : 'error'
        ]
        return Api.error(this, 405, statusResponse)
    }
}
