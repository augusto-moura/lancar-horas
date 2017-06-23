package zg.augusto

import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.DateTime

class RequisicoesMudancaRegistroController {

    @Secured(['ROLE_USER'])
    def 'exibir-nova-requisicao'() {
        return [
            registro: Registro.get(params.id as Long)
        ]
    }

    @Secured(['ROLE_USER'])
    def 'nova-requisicao'() {
        def requisicao = new RequisicaoAlteracaoRegistro(
            justificativa: params.justificativa,
            dataMudanca: new DateTime(params.dataMudanca as String).toDate(),
        )

        if (requisicao.hasErrors()) {
            flash.errors = requisicao.errors
        } else {
            flash.success = ['Solicitação criada com sucesso!']
            requisicao.save()
        }

        redirect(controller: 'registros', action: 'meus-registros')
    }

}
