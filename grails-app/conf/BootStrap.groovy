import com.herokuapp.rangout.User
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(User) {
            def usrJson = [:]

            usrJson['id'] = it.id
            usrJson['oAuth'] = it.oAuth
            return usrJson
        }

    }
    def destroy = {
    }
}