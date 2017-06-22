package zg.augusto

class ParametroSistema {

    static final String CHAVE_PARAMETRO_ADICIONAL_NOTURNO = 'ADICIONAL_NOTURNO'
    static final String CHAVE_PARAMETRO_ADICIONAL_FERIADO = 'ADICIONAL_FERIADO'

    static mapping = {
        table 'PARAMETROS_SISTEMA'
        version false
    }

    static constraints = {
        chave blank: false, maxSize: 255
        valor maxSize: 255
    }

    String chave
    String valor

}
