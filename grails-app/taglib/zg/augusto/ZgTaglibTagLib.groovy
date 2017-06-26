package zg.augusto

class ZgTaglibTagLib {

    static namespace = "zg"

    def pagination = { attrs ->
        out << render(template: "/templates/pagination", model: attrs)
    }

}
