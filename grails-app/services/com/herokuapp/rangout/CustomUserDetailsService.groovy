package com.herokuapp.rangout

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.transaction.Transactional
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Transactional
class CustomUserDetailsService extends GormUserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee.withTransaction { status ->

            Employee employee = Employee.findByUsername(username)
            if (!employee)
                throw new UsernameNotFoundException('User not found', username)

            def authorities = employee.authorities.collect {
                new GrantedAuthorityImpl(it.authority)
            }

            boolean enabled = true
            boolean accountExpired  = true
            boolean accountLocked   = true
            boolean passwordExpired = true

            return new CustomUserDetails(employee.username, employee.password, employee.id, enabled,
                    accountExpired, accountLocked, passwordExpired, authorities)
        }
    }

    UserDetails loadUserByUsernameAndEstablishment(String username, String establishment) throws UsernameNotFoundException {

    }
}
