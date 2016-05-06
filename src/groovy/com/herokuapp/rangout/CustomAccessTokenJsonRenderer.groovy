package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer
import grails.plugin.springsecurity.userdetails.GrailsUser

class CustomAccessTokenJsonRenderer implements AccessTokenJsonRenderer {

    @Override
    String generateJson(AccessToken accessToken) {
        if (accessToken.authenticated) {
            Employee.withTransaction { status ->
                def roles = []
                def employee = Employee.findById(((GrailsUser) accessToken.principal).id)
                employee.authorities?.each { role ->
                    roles.add(role.authority)
                }

                def statusResponse = [
                        username: employee.username,
                        role: roles,
                        establishment: [
                                id: employee.establishment.id,
                                name: employee.establishment.name,
                                nickname: employee.establishment.nickname
                        ],
                        token: accessToken.accessToken,
                        expiration: 3600

                ]
                return statusResponse as JSON
            }
        }
    }
}
