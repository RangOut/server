class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/status"   (controller: "application", action: "index", method: "GET")
        "/api/register" (controller: "application", action: "register", method: "POST", parseRequest: true)

        "/api/establishment" (controller: "establishment", action: "list", method: "GET")

        "/api/establishment/$establishmentId/employee" (controller: "employee", action: "list", method: "GET")
        "/api/establishment/$establishmentId/employee" (controller: "employee", action: "save", method: "POST", parseRequest: true)

        "/api/establishment/$establishmentId/menu"  (controller: "establishment", action: "getMenu", method: "GET")
        "/api/establishment/$establishmentId/order" (controller: "establishment", action: "getOrders", method: "GET")

        "/api/establishment/employee/$employeeId" (controller: "employee", action: "delete", method: "DELETE")

        "/api/user/order"     (controller: "user", action: "makeOrder", method: "POST", parseRequest: true)
        "/api/user/register"  (controller: "user", action: "register", method: "POST", parseRequest: true)
        "/api/user/$socialId/order" (controller: "user", action: "getOrders", method: "GET")
        "/api/user/establishment/$establishmentId/menu"  (controller: "user", action: "getEstablishmentMenu", method: "GET")
        "/api/user/establishment/$establishmentId/table" (controller: "user", action: "selectTable", method: "POST", parseRequest: true)


        "/"   (view:"/index")
        "403" (controller: "error", action: "forbidden")
        "404" (controller: "error", action: "badRequest")
        "405" (controller: "error", action: "methodNotAllowed")
        "500" (view:'/error')
	}
}
