package zg.augusto

import grails.transaction.Transactional

@Transactional
class RequisacaoAlteracaoRegistroService {

    def novaRequisicao(Registro alvo, Date dataMudanca, String justificativa) {
        return new RequisicaoAlteracaoRegistro(registro: alvo, dataMudanca: dataMudanca, justificativa: justificativa).save()
    }

    def aceitarRequisicao(RequisicaoAlteracaoRegistro requisicao) {
        def registro = requisicao.registro
        registro.dataMarcada = requisicao.dataMudanca
        registro.save()

        requisicao.status = RequisicaoAlteracaoRegistro.Status.ACEITA
        return requisicao.save()
    }

    def recusarRequisicao(RequisicaoAlteracaoRegistro requisicao) {
        requisicao.status = RequisicaoAlteracaoRegistro.Status.RECUSADA
        return requisicao.save()
    }

}
