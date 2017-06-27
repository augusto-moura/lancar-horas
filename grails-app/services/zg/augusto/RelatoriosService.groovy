package zg.augusto

import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.transaction.Transactional
import org.joda.time.Duration
import zg.augusto.estrategias.bonussalarial.BonusHoraExtra
import zg.augusto.estrategias.bonussalarial.BonusNoturno
import zg.augusto.estrategias.bonussalarial.BonusSalarial
import zg.augusto.relatorios.Jornada
import zg.augusto.relatorios.RelatorioSalario
import zg.augusto.relatorios.TempoDeTrabalho

import javax.annotation.PostConstruct

@Transactional
class RelatoriosService {

    static final BONUS_NOTURNO = new BonusNoturno()
    def springSecurityService

    RelatorioSalario relatorioUsuarioLogado() {
        def grailsUser = springSecurityService.principal as GrailsUser
        def usuarioAtual = Usuario.findById(grailsUser.id as Long)
        def registrosUserAtual = Registro.findAllByUsuario(usuarioAtual)

        def bonusHoraExtra = new BonusHoraExtra(usuarioAtual, Feriado.findAll().collect { it.data })

        def jornadaNoPeriodo = new TempoDeTrabalho(usuarioAtual, registrosUserAtual).jornadasDiarias.collectMany { k, val -> val }
        def valorDaHora = usuarioAtual.calcularValorHora()

        def jornadaTotal = jornadaNoPeriodo.collect {
            Jornada it -> new Duration(it.entrada, it.saida).getMillis()
        }.sum() / BonusSalarial.MILIS_IN_HOUR

        def adicionalNoturno = BONUS_NOTURNO.maximoDeBonus(jornadaNoPeriodo)
        def adicionalHoraExtra = bonusHoraExtra.maximoDeBonus(jornadaNoPeriodo)

        return new RelatorioSalario(
            user: usuarioAtual,
            salarioBruto: jornadaTotal * valorDaHora,
            bonusNoturno: adicionalNoturno * valorDaHora,
            bonusHoraExtra: adicionalHoraExtra * valorDaHora,
            salarioFinal: (jornadaTotal * valorDaHora) + (adicionalNoturno * valorDaHora) + (adicionalHoraExtra * valorDaHora)
        )
    }

}
