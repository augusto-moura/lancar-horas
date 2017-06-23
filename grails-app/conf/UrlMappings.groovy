class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/home"(view: '/home')
        "/"(controller: 'login')

        "500"(view: '/error')
    }

}
