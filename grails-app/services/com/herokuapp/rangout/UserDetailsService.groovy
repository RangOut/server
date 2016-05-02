package com.herokuapp.rangout

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.transaction.Transactional
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Transactional
class UserDetailsService extends GormUserDetailsService {

    boolean enabled = true
    boolean accountExpired  = true
    boolean accountLocked   = true
    boolean passwordExpired = true

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee.withTransaction { status ->

            Employee employee = Employee.findByUsername(username)
            if (!employee)
                throw new UsernameNotFoundException('User not found', username)

            def authorities = employee.authorities.collect { new GrantedAuthorityImpl(it.authority) }

            return new GrailsUser(employee.username, employee.password,
                    enabled, accountExpired, passwordExpired, accountLocked, authorities, employee.id)
        }
    }

    UserDetails loadUserByUsernameAndEstablishment(String username, String establishment) throws UsernameNotFoundException {

        Employee.withTransaction { status ->

            Employee employee = Employee.findByEstablishmentAndUsername(Establishment.findByNickname(establishment), username)

            if (!employee)
                throw new UsernameNotFoundException('User not found', username)

            def authorities = employee.authorities.collect { new GrantedAuthorityImpl(it.authority) }

            return new GrailsUser(employee.username, employee.password,
                    enabled, accountExpired, passwordExpired, accountLocked, authorities, employee.id)
        }
    }
}
