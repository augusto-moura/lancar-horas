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
}
