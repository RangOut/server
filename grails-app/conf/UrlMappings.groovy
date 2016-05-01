class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/register" (controller: "application", action: "register", method: "POST", parseRequest: true)
        "/api/status"   (controller: "application", action: "index", method: "GET")

        "/api/establishment" (controller: "establishment", action: "list", method: "GET")

        "/api/establishment/$establishmentId/employee" (controller: "employee", action: "list", method: "GET")
        "/api/establishment/$establishmentId/employee" (controller: "employee", action: "save", method: "POST", parseRequest: true)

        "/api/establishment/$establishmentId/menu"  (controller: "establishment", action: "getMenu", method: "GET")
        "/api/establishment/$establishmentId/order" (controller: "establishment", action: "getOrders", method: "GET")

        "/api/establishment/$establishmentId/employee/$employeeId" (controller: "employee", action: "show", method: "GET")
        "/api/establishment/$establishmentId/employee/$employeeId" (controller: "employee", action: "update", method: "PUT")
        "/api/establishment/$establishmentId/employee/$employeeId" (controller: "employee", action: "delete", method: "DELETE")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
