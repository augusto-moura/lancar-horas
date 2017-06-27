package zg.augusto.estrategias.bonussalarial

import org.joda.time.LocalDate
import zg.augusto.Usuario
import zg.augusto.relatorios.Jornada

class BonusHoraExtra implements BonusSalarial {

    BonusHoraExtraDiaUtil bonusDiaUtil
    BonusHoraExtraFeriado bonusFeriado

    BonusHoraExtra(Usuario user, List<LocalDate> feriados) {
        this.bonusDiaUtil = new BonusHoraExtraDiaUtil(user)
        this.bonusFeriado = new BonusHoraExtraFeriado(feriados)
    }

    @Override
    Double horasComBonus(List<Jornada> jornadas) {
        return bonusFeriado.horasComBonus(jornadas) ?: bonusDiaUtil.horasComBonus(jornadas)
    }

    @Override
    Double maximoDeBonus(List<Jornada> jornadas) {
        return bonusFeriado.maximoDeBonus(jornadas) ?: bonusDiaUtil.maximoDeBonus(jornadas)
    }
}
