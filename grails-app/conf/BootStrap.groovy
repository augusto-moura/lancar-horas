import lancar.horas.RolesFuncionario
import lancar.horas.Usuario

class BootStrap {

    def init = { servletContext ->
        def augusto = new Usuario(
            nome: 'Augusto',
            cpf: '701.064.191-96',
            dataNascimento: new Date(97, 8, 27),
            senha: '012345',
            papel: RolesFuncionario.ADMIN,
        )

        augusto.save()
    }

    def destroy = {
    }
}
