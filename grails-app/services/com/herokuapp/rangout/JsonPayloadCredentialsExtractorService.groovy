package com.herokuapp.rangout

import grails.plugin.springsecurity.rest.credentials.AbstractJsonPayloadCredentialsExtractor
import grails.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import javax.servlet.http.HttpServletRequest

@Transactional
class JsonPayloadCredentialsExtractorService extends AbstractJsonPayloadCredentialsExtractor {

    UsernamePasswordAuthenticationToken extractCredentials(HttpServletRequest httpServletRequest) {
        def jsonBody = getJsonBody(httpServletRequest)

        if (jsonBody) {
            String username = jsonBody."${'username'}"
            String password = jsonBody."${'password'}"
            String establishment = jsonBody."${'establishment'}"

            log.debug "Extracted credentials from JSON payload. Username: ${username}, password: ${password?.size()?'[PROTECTED]':'[MISSING]'}"

            new CustomUsernamePasswordAuthenticationToken(username, password, establishment)
        } else {
            log.debug "No JSON body sent in the request"
            return null
        }
    }
}
