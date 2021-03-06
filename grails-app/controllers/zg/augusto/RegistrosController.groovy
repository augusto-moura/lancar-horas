package zg.augusto

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus

class RegistrosController {

    static MAX_USUARIOS_PER_PAGE = 10

    def registrosService
    def springSecurityService

    @Secured(['ROLE_ADMIN'])
    def usuario() {
        def offset = Math.max(params.offset ? params.int('offset') : 0, 0)
        def max = Math.min(params.max ? params.int('max') : 0, 100)
        def orderby = params.orderby ?: 'id'
        def asc = params.asc

        def resultado = Registro.withCriteria {
            eq 'usuario.id', (params.id as Long)

            firstResult offset
            maxResults max
            order orderby, asc ? 'asc' : 'desc'
        }

        return [
            resultado: resultado,
            quantidade: Registro.count(),
            ordernadoPor: orderby,
            maximo: max,
            offset: offset,
            usuario: Usuario.get(params.id as Long)
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def salvarPonto(Registro registro) {
        if (registro.hasErrors()) {
            render(status: HttpStatus.BAD_REQUEST.value(), text: [
                errors: registro.errors
            ] as JSON)
        }

        registro.save()
        render(registro as JSON)
    }

    @Secured(['ROLE_ADMIN'])
    def 'editar-data-marcada'() {
        def id = params.id as Long
        def registro = registrosService.alterarDataMarcada(new LocalDateTime(params.dataMarcada as String).toDate(), id)

        if (registro.hasErrors()) {
            flash.errors = registro.errors
            redirect(action: 'exibir-edicao', id: id)
            return
        }

        flash.success = ['Edição realizada com sucesso!']
        redirect(action: 'usuario', id: registro.usuario.id)
    }

    @Secured(['ROLE_ADMIN'])
    def 'exibir-edicao'() {
        return [entidade: Registro.read(params.id as Long)]
    }

    @Secured(['ROLE_USER'])
    def 'bater-ponto'() {
        def grailsuser = springSecurityService.principal as GrailsUser
        def usuarioAtual = Usuario.get(grailsuser.id as Long)

        registrosService.baterPonto(usuarioAtual)

        flash.success = ['Ponto registrado com sucesso!']
        redirect(action: 'meus-registros')
    }

    @Secured(['ROLE_USER'])
    def 'exibir-bater-ponto'() {}

    @Secured(['ROLE_REGISTRADOR'])
    def 'pontos'(Registro registro) {
        render(registro.save() as JSON)
    }

    @Secured(['ROLE_USER'])
    def 'meus-registros'() {
        def grailsuser = springSecurityService.principal as GrailsUser
        def max = Math.max(0, (params.max ?: 10) as Integer)
        def offset = Math.max(0, (params.offset ?: 0) as Integer)
        def total = Registro.withCriteria {
            eq 'usuario.id', (grailsuser.id as Long)
            projections {
                rowCount()
            }
        }


        def resultado = Registro.withCriteria {
            eq 'usuario.id', (grailsuser.id as Long)

            maxResults max
            firstResult offset
        }

        return [
            registros: resultado,
            idUsuario: grailsuser.id,
            total: total[0],
            max: max,
        ]
    }

}
