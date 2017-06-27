package zg.augusto.relatorios

import org.joda.time.DateTime
import org.joda.time.LocalDate
import zg.augusto.Registro
import zg.augusto.Usuario

class TempoDeTrabalho {

    Usuario usuario
    Map<LocalDate, List<Jornada>> jornadasDiarias

    TempoDeTrabalho(Usuario user, List<Registro> registros) {
        usuario = user
        jornadasDiarias = calcularJornadasDiarias(registros)
    }

    private static Map<LocalDate, List<Jornada>> calcularJornadasDiarias(List<Registro> registros) {
        registros.sort(false) {
            return it.dataMarcada
        }.groupBy {
            return new LocalDate(it.dataMarcada)
        }.inject([:]) {
            Map<LocalDate, List<Jornada>> mapaDiaJornadas,
            LocalDate diaAlvo,
            List<Registro> registrosDiaAlvo ->
                mapaDiaJornadas.put(diaAlvo, agruparEmJornadas(registrosDiaAlvo))
                return mapaDiaJornadas
        } as Map<LocalDate, List<Jornada>>
    }

    private static List<Jornada> agruparEmJornadas(List<Registro> registros) {
        def listaJornadas = registros.inject([]) {
            List<Jornada> listaAccJornadas,
            Registro registro ->
                if (listaAccJornadas.isEmpty()) {
                    listaAccJornadas.add(new Jornada(entrada: new DateTime(registro.dataMarcada)))
                    return listaAccJornadas
                }

                def jornadaAnterior = listaAccJornadas.last()

                if (jornadaAnterior.saida == null) {
                    jornadaAnterior.saida = new DateTime(registro.dataMarcada)
                } else {
                    listaAccJornadas.add(new Jornada(entrada: new DateTime(registro.dataMarcada)))
                }

                return listaAccJornadas
        } as List<Jornada>

        if (listaJornadas.last().saida == null) {
            listaJornadas.pop()
        }

        return listaJornadas
    }

}
