import com.herokuapp.rangout.Employee
import grails.rest.render.json.JsonRenderer

// Place your Spring DSL code here
beans = {
    employeeListRenderer(JsonRenderer, Employee) {
        namedConfiguration = 'employeeList'
    }

    employeeSaveRenderer(JsonRenderer, Employee) {
        namedConfiguration = 'employeeSave'
    }
}
