package com.herokuapp.rangout

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Object establishment

    CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials)
    }

    CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Object establishment) {
        this(principal, credentials)
        this.establishment = establishment
    }

    public Object getEstablishment() {
        return establishment
    }
}
