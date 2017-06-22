package zg.augusto

import grails.transaction.Transactional

@Transactional
class RegistrosService {

    def alterarDataMarcada(Date data, Long idRegistro) {
        def registro = Registro.get(idRegistro)
        registro.dataMarcada = data

        registro.save()

        return registro
    }

    def baterPonto(Usuario user) {
        def rezistro = new Registro(usuario: user)
        return rezistro.save()
    }

}
