package zg.augusto

import grails.plugin.springsecurity.annotation.Secured

class UsuariosController {

    static MAX_USUARIOS_PER_PAGE = 10

    def usuariosService

    @Secured(['ROLE_ADMIN'])
    def listar() {
        def nome = params.buscarNome
        def max = Math.max(0, (params.max ?: 10) as Integer)
        def offset = Math.max(0, (params.offset ?: 0) as Integer)
        def total = Usuario.withCriteria {
            if (nome) ilike "nome", "%$nome%"
            projections {
                rowCount()
            }
        }

        return [
            resultado: Usuario.withCriteria {
                if (nome) ilike "nome", "%$nome%"

                firstResult offset
                maxResults max
            },
            nomeBuscado: nome,
            total: total[0],
            max: max,
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def 'exibir-cadastrar-novo'() {}

    @Secured(['ROLE_ADMIN'])
    def 'cadastrar-novo'() {
        def usuario = usuariosService.cadastrarNovoUsuario(new Usuario(
            username: params.username as String,
            password: params.password as String,
            nome: params.nome as String,
            cpf: params.cpf as String,
            dataNascimento: new Date(
                params.dataNascimento_year as Integer,
                params.dataNascimento_month as Integer,
                params.dataNascimento_day as Integer,
            ),
            salario: params.salario as BigDecimal,
            cargaHorariaDiaria: params.cargaHorariaDiaria as Long,
        ))

        if (usuario.hasErrors()) {
            flash.errors = usuario.errors
            redirect(action: 'exibir-cadastrar-novo')
        } else {
            flash.success = ['Usuário cadastrado com sucesso!']
        }

        redirect(action: 'listar')
    }

}
