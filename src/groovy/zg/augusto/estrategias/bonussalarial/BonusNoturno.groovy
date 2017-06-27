package zg.augusto.estrategias.bonussalarial

import org.joda.time.DateTime
import org.joda.time.Interval
import zg.augusto.relatorios.Jornada

class BonusNoturno implements BonusSalarial {

    static final Double MULTIPLICADOR = 0.2

    @Override
    Double horasComBonus(List<Jornada> jornadas) {
        return jornadas.collect(this.&horasComBonusJornada).sum() as Double
    }

    @Override
    Double maximoDeBonus(List<Jornada> jornadas) {
        return horasComBonus(jornadas) * MULTIPLICADOR
    }

    static private Double horasComBonusJornada(Jornada jornada) {
        def intervaloNoturnoAnterior = new Interval(
            new DateTime(jornada.entrada).minusDays(1).withHourOfDay(22).hourOfDay().roundFloorCopy(),
            new DateTime(jornada.entrada).withHourOfDay(5).hourOfDay().roundFloorCopy(),
        )
        def intervaloNoturnoPosterior = new Interval(
            new DateTime(jornada.entrada).withHourOfDay(22).hourOfDay().roundFloorCopy(),
            new DateTime(jornada.entrada).plusDays(1).withHourOfDay(5).hourOfDay().roundFloorCopy(),
        )
        def intervaloJornada = new Interval(jornada.entrada, jornada.saida)

        def overlapAnterior = intervaloNoturnoAnterior.overlap(intervaloJornada)
        def overlapPosterior = intervaloNoturnoPosterior.overlap(intervaloJornada)

        def milisEmBonus = (overlapAnterior?.toDurationMillis() ?: 0) + (overlapPosterior?.toDurationMillis() ?: 0)

        return milisEmBonus / MILIS_IN_HOUR
    }

}
