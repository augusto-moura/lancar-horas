package zg.augusto

class RequisicaoAlteracaoRegistro {

    static belongsTo = [registro: Registro]

    static mapping = {
        table 'REQUISICOES_ALTERACAO_REGISTRO'
        version false
    }

    static constraints = {
    }

    String justificativa
    Registro registro
    Date dataMudanca

}
