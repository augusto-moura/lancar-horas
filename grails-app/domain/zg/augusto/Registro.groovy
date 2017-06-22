package zg.augusto

class Registro {

    static belongsTo = [usuario: Usuario]

    static mapping = {
        table 'REGISTROS'
        version false

        usuario lazy: false
    }

    Date dataMarcada = new Date()
    Date dateCreated

    def beforeInsert() {
        dataMarcada = new Date()
    }

}
