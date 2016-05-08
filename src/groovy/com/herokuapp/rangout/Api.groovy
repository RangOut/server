package com.herokuapp.rangout

import grails.converters.JSON

class Api {

    static ok(controller, data=null) {
        if (data != null)
            controller.render(status: 200, contentType: 'application/json') { data as JSON }

        controller.render(status: 200)
    }

    static error(controller, code=null, data=null) {
        if (code == null)
            code = 500
        if (data != null)
            controller.render(status: code, contentType: 'application/json') { data as JSON }

        controller.render(status: code)
    }
}