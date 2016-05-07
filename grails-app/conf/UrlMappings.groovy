class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/status"   (controller: "application", action: "index")
        "/api/register" (controller: "application", action: "register", parseRequest: true)

        "/api/establishment" (controller: "establishment", action: "list")

        "/api/establishment/$establishmentId/employee" (controller: "employee", action: "list")
        "/api/establishment/$establishmentId/employee" (controller: "employee", action: "save", parseRequest: true)

        "/api/establishment/$establishmentId/menu"  (controller: "establishment", action: "getMenu")
        "/api/establishment/$establishmentId/order" (controller: "establishment", action: "getOrders")

        "/api/establishment/$establishmentId/employee/$employeeId" (controller: "employee", action: "show")
        "/api/establishment/$establishmentId/employee/$employeeId" (controller: "employee", action: "update")
        "/api/establishment/$establishmentId/employee/$employeeId" (controller: "employee", action: "delete")

        "/api/user/order"     (controller: "user", action: "makeOrder", parseRequest: true)
        "/api/user/register"  (controller: "user", action: "register", parseRequest: true)
        "/api/user/$socialId" (controller: "user", action: "getOrders")
        "/api/user/establishment/$establishmentId/menu"  (controller: "user", action: "getEstablishmentMenu")
        "/api/user/establishment/$establishmentId/table" (controller: "user", action: "selectTable", parseRequest: true)


        "/"   (view:"/index")
        "403" (controller: "error", action: "forbidden")
        "500" (view:'/error')
	}
}
