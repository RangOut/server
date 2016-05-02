package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer

class CustomAccessTokenJsonRenderer implements AccessTokenJsonRenderer {

    @Override
    String generateJson(AccessToken accessToken) {
        return accessToken.principal as JSON
    }
}
