class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/"(view:"/index-custom")
        "500"(view:'/error')

        "/states" (resources: "state", excludes:['delete', 'update'])
        // "/states"(resources:"state", controller:"simple", view:"simple")

	}
}
