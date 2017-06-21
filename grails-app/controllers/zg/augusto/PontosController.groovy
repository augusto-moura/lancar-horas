package zg.augusto

import grails.converters.JSON
import org.hibernate.FetchMode
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import zg.augusto.dominio.serializacao.ConsultaMultipla

import javax.transaction.Transactional

class PontosController {

    RegistrosService registrosService

    def usuario() {
        def offset = Math.max(params.offset ? params.int('offset') : 0, 0)
        def max = Math.min(params.max ? params.int('max') : 0, 100)
        def orderby = params.orderby ?: 'id'
        def asc = params.asc

        def resultado = Registro.withCriteria {
            eq 'usuario.id', Long.valueOf(params.id as String)

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
        ]
    }

    def salvarPonto(Registro registro) {
        if (registro.hasErrors()) {
            render(status: HttpStatus.BAD_REQUEST.value(), text: [
                errors: registro.errors
            ] as JSON)
        }

        registro.save()
        render(registro as JSON)
    }

    def 'editar-data-marcada'() {
        def id = params.id as Long
        def registro = registrosService.alterarDataMarcada(new LocalDateTime(params.dataMarcada as String).toDate(), id)

        if (registro.hasErrors()) {
            flash.errors = registro.errors
            redirect(action: 'exibir-edicao', id: id)
        }

        flash.success = ['Edição realizada com sucesso!']
        render(view: 'exibir-edicao', model: [entidade: registro])
    }

    def 'exibir-edicao'() {
        return [entidade: Registro.read(params.id as Long)]
    }

}
