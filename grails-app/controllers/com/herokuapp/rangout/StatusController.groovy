package com.herokuapp.rangout

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StatusController {
    static allowedMethods = [index: "GET"]

    def grailsApplication

    @Secured(["permitAll"])
    def index() {
        def statusResponse = [
                'api' : [
                        'app'           : grailsApplication.metadata['app.name'],
                        'version'       : grailsApplication.metadata['app.version'],
                        'build'         : grailsApplication.metadata['app.buildId'],
                        'lastUpdated'   : grailsApplication.metadata['app.buildDate']],
                'status' : "up"
        ]
        Api.ok(this, statusResponse)
    }
}