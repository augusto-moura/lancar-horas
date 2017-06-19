package zg.augusto

import grails.converters.JSON
import org.hibernate.FetchMode
import zg.augusto.dominio.serializacao.ConsultaMultipla

class LancamentosController {

    def index() {
        def offset = Math.max(params.offset ? params.int('offset') : 0, 0)
        def max = Math.min(params.max ? params.int('max') : 0, 100)
        def orderby = params.orderby ?: 'id'
        def asc = params.asc

        def resultado = new ConsultaMultipla<Registro>(
            resultados: Registro.withCriteria {
                firstResult offset
                order orderby, asc ? 'asc' : 'desc'
                maxResults max
                fetchMode'usuario', FetchMode.SELECT
            },
            quantidade: Registro.count(),
            ordernadoPor: orderby,
            maximo: max,
            offset: offset,
        )

        render(resultado as JSON)
    }

}
