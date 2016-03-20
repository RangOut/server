import com.herokuapp.rangout.Establishment

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/api/users" (controller: "user", action: "list", method: "GET")
//        "/api/users" (controller: "user", action: "save", method: "POST")

        "/api/establishments" (controller: "establishment", action: "list", method: "GET")
        "/api/establishments" (controller: "establishment", action: "save", method: "POST")

        "/api/establishments/$establishment_id/employees" (controller: "employee", action: "list", method: "GET")
        "/api/establishments/$establishment_id/employees" (controller: "employee", action: "save", method: "POST")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
