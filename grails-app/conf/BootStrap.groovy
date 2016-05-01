import com.herokuapp.rangout.Role
import com.herokuapp.rangout.Employee
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        if (Role.count == 0) {
            def userRole = new Role(authority: 'ROLE_USER')
            userRole.save(failOnError: true)

            def managerRole = new Role(authority: 'ROLE_MANAGER')
            managerRole.save(failOnError: true)

            def employeeRole = new Role(authority: 'ROLE_EMPLOYEE')
            employeeRole.save(failOnError: true)
        }
    }

    def destroy = {
    }
}