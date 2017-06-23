package zg.augusto

import grails.plugin.springsecurity.annotation.Secured

class UsuariosController {

    @Secured(['ROLE_ADMIN'])
    def listar() {
        def nome = params.buscarNome
        return [
            resultado: Usuario.withCriteria {
                if (nome) {
                    ilike("nome","%$nome%")
                }
            },
            nomeBuscado: nome,
        ]
    }

}
