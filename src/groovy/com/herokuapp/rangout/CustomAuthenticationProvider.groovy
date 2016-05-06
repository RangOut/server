package com.herokuapp.rangout

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

class CustomAuthenticationProvider implements AuthenticationProvider {

    def springSecurityService
    SecurityService securityService
    UserDetailsService customUserDetails

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username      = authentication.getPrincipal()
        String password      = authentication.getCredentials()
        String establishment = ((CustomUsernamePasswordAuthenticationToken) authentication).getEstablishment()

        def userDetails
        if (establishment == null)
            userDetails = customUserDetails.loadUserByUsername(username)
        else
            userDetails = customUserDetails.loadUserByUsernameAndEstablishment(username, establishment)

        if (!securityService.isPasswordValid(password, userDetails.password))
            throw new BadCredentialsException("Wrong password.", password);

        def authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities())

        authenticationToken.setDetails(authentication.details)
        return authenticationToken
    }

    @Override
    boolean supports(Class<?> aClass) {
        return true
    }
}
