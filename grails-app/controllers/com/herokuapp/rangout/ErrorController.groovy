package com.herokuapp.rangout

import grails.plugin.springsecurity.annotation.Secured

@Secured(['denyAll'])
class ErrorController {

    def forbidden() {
        def statusResponse = [
                message: 'Access to the specified resource has been forbidden',
                status : 'error'
        ]
        return Api.error(this, 403, statusResponse)
    }
}
