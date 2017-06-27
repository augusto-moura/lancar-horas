package zg.augusto.estrategias.bonussalarial

import zg.augusto.relatorios.Jornada

interface BonusSalarial {

    static final Long MILIS_IN_HOUR = 1000 * 60 * 60

    abstract Double horasComBonus(List<Jornada> jornadas)

    abstract Double maximoDeBonus(List<Jornada> jornadas)

}
