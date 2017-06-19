package lancar.horas

import org.joda.time.LocalDate

class Funcionario {

    static hasMany = [registros: Registro]

    static constraints = {
    }

    static mapping = {
        table 'FUNCIONARIO'
        id column: 'ID_FUNCIONARIO'

        pessoa column: 'ID_PESSOA'
        dataAdmissao column: 'DT_ADMISSAO'
        papel column: 'IN_PAPEL'
    }

    Pessoa pessoa
    Date dataAdmissao
    RolesFuncionario papel

    String toString() {
        return pessoa.toString()
    }

}
