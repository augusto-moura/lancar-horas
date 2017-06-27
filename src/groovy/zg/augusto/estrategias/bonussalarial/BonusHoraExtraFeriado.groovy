package zg.augusto.estrategias.bonussalarial

import org.joda.time.Duration
import org.joda.time.LocalDate
import zg.augusto.relatorios.Jornada

class BonusHoraExtraFeriado implements BonusSalarial {

    static final Double MULTIPLICADOR = 1

    List<LocalDate> datasFeriado

    BonusHoraExtraFeriado(List<LocalDate> datasFeriado) {
        this.datasFeriado = datasFeriado
    }

    @Override
    Double horasComBonus(List<Jornada> jornadas) {
        if (datasFeriado.any { jornadas[0]?.entrada?.toLocalDate() == it }) {
            def milisJornadas = jornadas.collect { new Duration(it.entrada, it.saida).getMillis() }.sum()
            return milisJornadas / MILIS_IN_HOUR
        }

        return 0
    }

    @Override
    Double maximoDeBonus(List<Jornada> jornadas) {
        return horasComBonus(jornadas) * MULTIPLICADOR
    }
}
