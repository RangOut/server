class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/users" (controller: "user", action: "list", method: "GET")
        "/api/users" (controller: "user", action: "save", method: "POST")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
