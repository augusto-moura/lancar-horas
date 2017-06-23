package zg.augusto

import grails.transaction.Transactional

@Transactional
class RequisacaoAlteracaoRegistroService {

    def novaRequisicao(Registro alvo, Date dataMudanca, String justificativa) {
        return new RequisicaoAlteracaoRegistro(registro: alvo, dataMudanca: dataMudanca, justificativa: justificativa).save()
    }
}
