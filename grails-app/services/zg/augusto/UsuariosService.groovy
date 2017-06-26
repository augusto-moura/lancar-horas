package zg.augusto

import grails.transaction.Transactional

@Transactional
class UsuariosService {

    def cadastrarNovoUsuario(Usuario usuario) {
        def userRole = SecRole.findByAuthority('ROLE_USER')
        def usuarioPersistido = usuario.save()

        new SecUserSecRole(secUser: usuarioPersistido, secRole: userRole).save()

        return usuarioPersistido
    }

}
