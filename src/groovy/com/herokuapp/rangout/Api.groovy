package com.herokuapp.rangout

import grails.converters.JSON

class Api {

    final static String nothing = ''

    static usernamePasswordNotSpecified(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') {
            new ApiResponse('Email/Username and password must be specified', 1010)
        }
    }

    static incorrectUsernameOrPassword(controller) {
        controller.response.status = 401
        controller.render(contentType: 'application/json') { new ApiResponse('Username / Password are incorrect', 1020) }
    }

    static incorrectFormat(controller, field=null, more=null) {
        def message = 'Incorrect format.'

        controller.response.status = 400
        if(field != null)
            message = field.toUpperCase() + ' is not in correct format.'
        if(more != null)
            message += ' ' + more

        controller.render(contentType: 'application/json') { new ApiResponse(message, 1020) }
    }

    static unexpected(controller, exception=null) {
        def message = ('Unexpected Error ' + exception?.message)?.trim()

        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse(message) }
    }

    static exception(controller, exception=null) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Unexpected Error: ' + exception?.message) }
    }

    static resourceUsed(controller, code=400, message='') {
        controller.render(status: code, contentType: 'application/json') {
            new ApiResponse(message, 2001)
        }
    }

    static missingToken(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Invalid or missing authentication token', 1010) }
    }

    static missingParameter(controller, more=null, paramList=null) {
        def message = 'Missing parameter.'

        controller.response.status = 400
        if(more != null)
            message += ' ' + more

        controller.render(contentType: 'application/json') { new ApiResponse(message, 1010, paramList) }
    }

    static providerNotSupported(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Cloud Account type not supported', 3004) }
    }

    static invalidProviderId(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Invalid Provider Id specified', 3000) }
    }

    static providerNotFound(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Cloud Account id not found') }
    }

    static invalidInstanceParameter(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Invalid Instance parameter specified', 4030) }
    }

    static invalidInstanceId(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Invalid Instance Id', 4000) }
    }

    static serviceNotFound(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Service alias not found') }
    }

    static incorrectServiceParameter(controller, parameter, allowedValues) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') {
            new ApiResponse("${parameter} is incorrect format, allowed values are: ${allowedValues}", 1020)
        }
    }

    static invalidUserServiceId(controller) {
        controller.response.status = 400
        controller.render(contentType: 'application/json') { new ApiResponse('Invalid UserService Id', 4000) }
    }

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