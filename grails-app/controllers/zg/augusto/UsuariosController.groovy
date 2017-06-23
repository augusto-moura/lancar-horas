package zg.augusto

import grails.plugin.springsecurity.annotation.Secured

class UsuariosController {

    @Secured(['ROLE_ADMIN'])
    def listar() {
        def nome = params.buscarNome
        def max = Math.max(0, params.max ?: 0)
        def offset = Math.max(0, params.max ?: 0)

        return [
            resultado: Usuario.withCriteria {
                if (nome) ilike "nome","%$nome%"

                firstResult offset
                maxResults max
            },
            nomeBuscado: nome,
        ]
    }

}
