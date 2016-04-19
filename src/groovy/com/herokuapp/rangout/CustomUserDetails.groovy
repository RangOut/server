package com.herokuapp.rangout

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

class CustomUserDetails extends GrailsUser {

    CustomUserDetails(String username, String password, long id, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, id)
    }
}
