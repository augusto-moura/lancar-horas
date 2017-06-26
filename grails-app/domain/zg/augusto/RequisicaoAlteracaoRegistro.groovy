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
    Status status = Status.PENDENTE

    static enum Status {
        PENDENTE, RECUSADA, ACEITA
    }

}
