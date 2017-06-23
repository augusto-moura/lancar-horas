package zg.augusto

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.DateTime

class RequisicoesMudancaRegistroController {

    def requisacaoAlteracaoRegistroService

    @Secured(['ROLE_USER'])
    def 'exibir-nova-requisicao'() {
        return [
            registro: Registro.get(params.id as Long)
        ]
    }

    @Secured(['ROLE_USER'])
    def 'nova-requisicao'() {
        def requisicao = requisacaoAlteracaoRegistroService.novaRequisicao(
            Registro.load(params.id as Long),
            new DateTime(params.dataMudanca as String).toDate(),
            params.justificativa as String,
        )

        if (requisicao.hasErrors()) {
            flash.errors = requisicao.errors
        } else {
            flash.success = ['Solicitação criada com sucesso!']
        }

        redirect(controller: 'registros', action: 'meus-registros')
    }

    @Secured(['ROLE_ADMIN'])
    def 'listar-requisicoes'() {
        return [requisicoes: RequisicaoAlteracaoRegistro.findAll()]
    }

}
