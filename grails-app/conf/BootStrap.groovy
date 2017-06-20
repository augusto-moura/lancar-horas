import org.apache.log4j.Logger
import zg.augusto.Registro
import zg.augusto.dominio.enums.RolesFuncionario
import zg.augusto.Usuario

class BootStrap {

    private static Logger LOGGER = Logger.getLogger(BootStrap)

    def init = { servletContext ->
        def augusto = new Usuario(
            nome: 'Augusto',
            cpf: '701.064.191-96',
            dataNascimento: new Date(97, 8, 27),
            senha: '012345',
            salario: 11_0000.0,
            papel: RolesFuncionario.ADMIN,
        )

        augusto.save()

        def registroFoo = new Registro(usuario: augusto)
        registroFoo.save()

        sleep(1000)

        def registroBar = new Registro(usuario: augusto)
        registroBar.save()

        sleep(1000)

        def registroQuux = new Registro(usuario: augusto)
        registroQuux.save()
    }

    def destroy = {
    }
}
