package zg.augusto

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser
import zg.augusto.relatorios.TempoDeTrabalho

class RelatoriosController {

    def relatoriosService

    @Secured(['ROLE_USER'])
    def 'meu-relatorio'() {
        def relatorio = relatoriosService.relatorioUsuarioLogado()
        return relatorio
    }

}
