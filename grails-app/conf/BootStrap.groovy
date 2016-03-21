import com.herokuapp.rangout.Employee
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        JSON.createNamedConfig('employeeList') {
            it.registerObjectMarshaller(Employee) { Employee employee ->
                def result = [:]

                result['id']        = employee.id
                result['name']      = employee.name
                result['username']  = employee.username
                result
            }
        }
        JSON.createNamedConfig('employeeSave') {
            it.registerObjectMarshaller(Employee) { Employee employee ->
                def result = [:]

                result['id'] = employee.id
                result['name'] = employee.name
                result['username'] = employee.username
                // Get information about employees's establishment
                final establishment = [id: employee.establishment.id, name: employee.establishment.name]
                result['establishment'] = establishment
                result
            }
        }
    }
    def destroy = {
    }
}