package lancar.horas

import org.joda.time.DateTime

class Registro {

    static belongsTo = [usuario: Usuario]

    static mapping = {
        table 'REGISTROS'
        id column: 'ID_REGISTRO'
        version false

        usuario column: 'ID_USUARIO'
        dateCreated column: 'DT_HORA_MARCADA'
    }

    Date dateCreated

}
