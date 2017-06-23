import grails.util.Environment
import zg.augusto.Registro
import zg.augusto.SecUserSecRole
import zg.augusto.SecRole
import zg.augusto.Usuario
import zg.augusto.Usuario.Roles
import zg.augusto.dominio.enums.RolesUsuario

class BootStrap {

    def init = { servletContext ->
        if (Environment.getCurrent() == Environment.DEVELOPMENT) {
            def userRole = SecRole.findByAuthority('ROLE_USER') ?:
                new SecRole(authority: 'ROLE_USER').save(failOnError: true)

            def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?:
                new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)


            def ana = new Usuario(
                username: 'ana',
                password: 'ana',
                enabled: true,

                nome: 'Ana',
                cpf: '600.043.680-70',
                dataNascimento: new Date(90, 6, 27),
                salario: 10_000.0,
            ).save(failOnError: true)

            SecUserSecRole.create(ana, adminRole)
            SecUserSecRole.create(ana, userRole)


            def bob = new Usuario(
                username: 'bob',
                password: 'bob',
                enabled: true,

                nome: 'Bob',
                cpf: '200.927.400-81',
                dataNascimento: new Date(98, 12, 8),
                salario: 8_000.0,
            ).save(failOnError: true)

            SecUserSecRole.create(bob, userRole)

            new Registro(usuario: bob).save(failOnError: true)
            sleep(1000)

            new Registro(usuario: bob).save(failOnError: true)
            sleep(1000)

            new Registro(usuario: bob).save(failOnError: true)
        }
    }

    def destroy = {
    }
}
