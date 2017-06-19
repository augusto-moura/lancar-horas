package zg.augusto

class Registro {

    static belongsTo = [usuario: Usuario]

    static mapping = {
        table 'REGISTROS'
        id column: 'ID_REGISTRO'
        version false

        usuario column: 'ID_USUARIO', lazy: false
        dateCreated column: 'DT_HORA_MARCADA'
    }

    Date dateCreated

}
