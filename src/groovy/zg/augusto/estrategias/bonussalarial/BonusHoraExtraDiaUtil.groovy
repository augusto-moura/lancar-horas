package zg.augusto.estrategias.bonussalarial

import org.joda.time.Duration
import zg.augusto.Usuario
import zg.augusto.relatorios.Jornada

class BonusHoraExtraDiaUtil implements BonusSalarial {

    static final Double MULTIPLICADOR = 0.5

    Usuario user

    BonusHoraExtraDiaUtil(Usuario user) {
        this.user = user
    }

    @Override
    Double horasComBonus(List<Jornada> jornadas) {
        def diferenca = somaHorasTrabalhadas(jornadas) - user.cargaHorariaDiaria
        return diferenca > 0 ? diferenca : 0
    }

    @Override
    Double maximoDeBonus(List<Jornada> jornadas) {
        return horasComBonus(jornadas) * MULTIPLICADOR
    }

    private static Double somaHorasTrabalhadas(List<Jornada> jornadas) {
        def milisTotais = jornadas.collect {
            return new Duration(it.entrada, it.saida).getMillis()
        }.sum() as Long
        return milisTotais / MILIS_IN_HOUR
    }
}
