package zg.augusto

class Registro {

    static belongsTo = [usuario: Usuario]

    static mapping = {
        table 'REGISTROS'
        id column: 'ID_REGISTRO'
        version false

        usuario column: 'ID_USUARIO', lazy: false
        dataMarcada column: 'DT_HORA_MARCADA'
        dateCreated column: 'DT_CRIACAO'
    }

    Date dataMarcada = new Date()
    Date dateCreated

    def beforeInsert() {
        dataMarcada = new Date()
    }

}
