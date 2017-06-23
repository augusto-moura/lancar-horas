package zg.augusto

import grails.plugin.springsecurity.annotation.Secured

class RequisicoesMudancaRegistroController {

    @Secured(['ROLE_USER'])
    def 'exibir-requisicao-mudancas-ponto'() {
        def id = params.id

        if (!id) {
            return [:]
        }

        return [registros: RequisicaoAlteracaoRegistro.withCriteria {
            createAlias('registro.usuario', 'usr')
            eq('usr.id', id)
        }]
    }

    def 'exibir-nova-requisicao'() {}
}
