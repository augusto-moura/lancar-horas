package zg.augusto

class ZgTaglib {

    static namespace = "zg"

    def pagination = { attrs ->
        out << render(template: "/templates/pagination", model: attrs)
    }

}
