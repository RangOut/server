import com.herokuapp.rangout.Role
import com.herokuapp.rangout.Employee
import com.herokuapp.rangout.Establishment
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.createNamedConfig('employeeList') {
            it.registerObjectMarshaller(Employee) { Employee employee ->
                def resultSet = [:]

                resultSet['id']        = employee.id
                resultSet['name']      = employee.name
                resultSet['username']  = employee.username
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
        JSON.createNamedConfig('establishmentSave') {
            it.registerObjectMarshaller(Establishment) { Establishment establishment ->
                def resultSet = [:]

                resultSet['id'] = establishment.id
                resultSet['name'] = establishment.name
                resultSet['nickname'] = establishment.nickname

                if(establishment.cnpj != null)
                    resultSet['cnpj'] = establishment.cnpj

                // Get information about establishment's address
                final address = [cep: establishment.address.cep,
                                 street: establishment.address.street,
                                 number: establishment.address.number,
                                 neighborhood: establishment.address.neighborhood,
                                 city: establishment.address.city,
                                 state: establishment.address.state,
                                 country: establishment.address.country,
                                 complement: establishment.address.complement]

                if(address['cep'] == null) address.remove('cep')
                if(address['complement'] == null) address.remove('complement')

                resultSet['address'] = address
                // Get information about establishment's contact
                resultSet['telephones'] = establishment.telephones
                if(!establishment.cellphones.isEmpty())
                    resultSet['cellphones'] = establishment.cellphones

                resultSet
            }
        }
        def userRole = new Role(authority: 'USER_ROLE')
        userRole.save(failOnError: true)

        def managerRole  = new Role(authority: 'MANAGER_ROLE')
        managerRole.save(failOnError: true)

        def employeeRole = new Role(authority: 'EMPLOYEE_ROLE')
        employeeRole.save(failOnError: true)
    }

    def destroy = {
    }
}