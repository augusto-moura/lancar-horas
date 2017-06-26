package zg.augusto

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.DateTime

class RequisicoesMudancaRegistroController {

    static MAX_USUARIOS_PER_PAGE = 10

    def requisacaoAlteracaoRegistroService

    @Secured(['ROLE_USER'])
    def 'exibir-nova-requisicao'() {
        return [
            registro: Registro.get(params.id as Long)
        ]
    }

    @Secured(['ROLE_USER'])
    def 'nova-requisicao'() {
        def requisicao = new RequisicaoAlteracaoRegistro(
            registro: Registro.load(params.id as Long),
            dataMudanca: new DateTime(params.dataMudanca as String).toDate(),
            justificativa: params.justificativa as String
        )

        if (!requisicao.validate()) {
            flash.errors = requisicao.errors
        } else {
            requisacaoAlteracaoRegistroService.novaRequisicao(requisicao)
            flash.success = ['Solicitação criada com sucesso!']
        }

        redirect(action: 'listar-requisicoes')
    }

    @Secured(['ROLE_ADMIN'])
    def 'listar-requisicoes'() {
        def max = Math.max(0, (params.max ?: 10) as Integer)
        def offset = Math.max(0, (params.offset ?: 0) as Integer)
        def total = RequisicaoAlteracaoRegistro.countByStatus(RequisicaoAlteracaoRegistro.Status.PENDENTE)

        return [
            requisicoes: RequisicaoAlteracaoRegistro.withCriteria {
                eq("status", RequisicaoAlteracaoRegistro.Status.PENDENTE)

                maxResults max
                firstResult offset
            },
            total: total,
            max: max,
            offset: offset,
        ]
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
