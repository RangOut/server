package com.herokuapp.rangout

import grails.converters.JSON

class ApiResponse {

    def message = ""
    def errorCode = null
    def parameters = null

    ApiResponse(message, errorCode=null, parameters=null) {
        this.message = message
        this.parameters = parameters
        this.errorCode  = errorCode
    }

    String toString() {
        if(parameters) {
            return [message: this.message, parameters: this.parameters, errorCode: this.errorCode] as JSON
        } else {
            return [message: this.message, errorCode: this.errorCode] as JSON
        }
    }
}
