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

        redirect(action: 'listar-requisicoes')
    }

    @Secured(['ROLE_ADMIN'])
    def 'listar-requisicoes'() {
        return [requisicoes: RequisicaoAlteracaoRegistro.findAllByStatus(RequisicaoAlteracaoRegistro.Status.PENDENTE)]
    }

    @Secured(['ROLE_ADMIN'])
    def 'aceitar-requisicao'() {
        def entidade = RequisicaoAlteracaoRegistro.get(params.id as Long)

        if (entidade) {
            flash.success = ['Requisição aceita com sucesso!']
            requisacaoAlteracaoRegistroService.aceitarRequisicao(entidade)
        } else {
            flash.errors = ['Entidade não encontrada']
        }

        redirect(action: 'listar-requisicoes')
    }

    @Secured(['ROLE_ADMIN'])
    def 'recusar-requisicao'() {
        def entidade = RequisicaoAlteracaoRegistro.get(params.id as Long)

        if (entidade) {
            flash.success = ['Requisição rejeitada com sucesso!']
            requisacaoAlteracaoRegistroService.recusarRequisicao(entidade)
        } else {
            flash.errors = ['Entidade não encontrada']
        }

        redirect(action: 'listar-requisicoes')
    }

}
