package zg.augusto

class UsuariosController {

    def listar() {
        def nome = params.buscarNome
        return [
            resultado: Usuario.withCriteria {
                if (nome) {
                    ilike("nome","%$nome%")
                }
            },
            nomeBuscado: nome,
        ]
    }

}
