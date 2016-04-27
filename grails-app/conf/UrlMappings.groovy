class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/register" (controller: "application", action: "signup", method: "POST", parseRequest: true)
        "/api/status"   (controller: "application", action: "index")

        "/api/establishments" (controller: "establishment", action: "list", method: "GET")

        "/api/establishments/$establishment_id/employees" (controller: "employee", action: "list", method: "GET")
        "/api/establishments/$establishment_id/employees" (controller: "employee", action: "save", method: "POST")
        "/api/establishments/$establishment_id/employees/$employees_id" (controller: "employee", action: "show", method: "GET")
        "/api/establishments/$establishment_id/employees/$employees_id" (controller: "employee", action: "update", method: "PUT")
        "/api/establishments/$establishment_id/employees/$employees_id" (controller: "employee", action: "delete", method: "DELETE")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
