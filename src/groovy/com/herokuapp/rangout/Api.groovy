package com.herokuapp.rangout

import grails.converters.JSON

class Api {

    final static String nothing = ''

    static ok(controller, data=null) {
        def message = nothing

        if (data != null)
            message = data as JSON

        controller.render(status: 200, contentType: 'application/json') { message }
    }

    static error(controller, code=null, data=null) {
        def message = nothing

        if (code == null)
            code = 500
        if (data != null)
            message = data as JSON

        controller.render(status: code, contentType: 'application/json') { message }
    }
}