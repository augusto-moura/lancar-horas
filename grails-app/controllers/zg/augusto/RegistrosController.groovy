package zg.augusto

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import zg.augusto.dominio.enums.RolesUsuario

class RegistrosController {

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
        render(view: 'exibir-edicao', model: [entidade: registro])
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
        render(view: '/home')
    }

    @Secured(['ROLE_USER'])
    def 'exibir-bater-ponto'() {}

}
