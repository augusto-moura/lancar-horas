package zg.augusto

class UsuariosController {

    def listar() {
        return [
            resultado: Usuario.findAll()
        ]
    }

}
