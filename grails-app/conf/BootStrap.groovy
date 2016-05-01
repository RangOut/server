import com.herokuapp.rangout.Role
import com.herokuapp.rangout.Employee
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.createNamedConfig('employeeList') {
            it.registerObjectMarshaller(Employee) { Employee employee ->
                def resultSet = [:]

                resultSet['id'] = employee.id
                resultSet['name'] = employee.name
                resultSet['username'] = employee.username

                resultSet
            }
        }
        JSON.createNamedConfig('employeeSave') {
            it.registerObjectMarshaller(Employee) { Employee employee ->
                def resultSet = [:]

                resultSet['id'] = employee.id
                resultSet['name'] = employee.name
                resultSet['username'] = employee.username

                // Get information about employees's establishment
                final establishment = [id: employee.establishment.id, name: employee.establishment.name]

                resultSet['establishment'] = establishment

                resultSet
            }
        }

        if (Role.count == 0) {
            def userRole = new Role(authority: 'USER_ROLE')
            userRole.save(failOnError: true)

            def managerRole = new Role(authority: 'MANAGER_ROLE')
            managerRole.save(failOnError: true)

            def employeeRole = new Role(authority: 'EMPLOYEE_ROLE')
            employeeRole.save(failOnError: true)
        }
    }

    def destroy = {
    }
}