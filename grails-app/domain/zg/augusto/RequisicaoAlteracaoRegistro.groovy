package zg.augusto

class RequisicaoAlteracaoRegistro {

    static belongsTo = [registro: Registro]

    static mapping = {
        table 'REQUISICOES_ALTERACAO_REGISTRO'
        version false

        justificativa type: 'text'
    }

    static constraints = {
        dataMudanca nullable: false
        justificativa blank: false
        status nullable: false
    }

    String justificativa
    Registro registro
    Date dataMudanca
    StatusSolicitacao status = StatusSolicitacao.PENDENTE

    static enum StatusSolicitacao {
        PENDENTE, RECUSADA, ACEITA
    }

}
