package zg.augusto

import grails.converters.JSON
import org.hibernate.FetchMode
import org.springframework.http.HttpStatus
import zg.augusto.dominio.serializacao.ConsultaMultipla

class PontosController {

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

    def editar(Registro registro) {
        if (registro.hasErrors()) {
            return [
                errors: registro.errors,
            ]
        }

        if (registro && registro.id) {
            registro.save()
        }

        return [entidade: registro]
    }

    def 'exibir-edicao'() {
        return [entidade: Registro.read(params.id as Long)]
    }

}
