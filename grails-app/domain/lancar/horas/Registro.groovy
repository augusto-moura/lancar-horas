package lancar.horas

import org.joda.time.DateTime

class Registro {

    static belongsTo = [funcionario: Funcionario]

    static mapping = {
        table 'REGISTRO'
        id column: 'ID_REGISTRO'

        funcionario column: 'ID_FUNCIONARIO'
        dateCreated column: 'DT_HORA_MARCADA'
    }

    Date dateCreated

}
