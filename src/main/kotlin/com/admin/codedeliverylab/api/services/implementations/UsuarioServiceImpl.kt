package com.admin.codedeliverylab.api.services.implementations


import com.admin.codedeliverylab.api.entities.Usuario
import com.admin.codedeliverylab.api.repository.UsuarioRepository
import com.admin.codedeliverylab.api.services.interfaces.UsuarioService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl(
    private val usuarioRepository: UsuarioRepository
) : UsuarioService {

    override fun saveUsuario(usuario: Usuario): Usuario {
        try {
            return usuarioRepository.save(usuario)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun getUsuarios(): List<Usuario> {
        try {
            return usuarioRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun validateLoginUsuario(login: String, senha: String): Usuario? {
        try {
            return usuarioRepository.findByLoginAndSenha(login , senha )
        } catch (ex: Exception) {
            throw ex
        }
    }


    override fun getUsuarioById(id: Long): Usuario? {
        try {
            return usuarioRepository.findByIdOrNull(id) ?: throw Exception("Usuário não encotrado")
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun deleteUsuario(id: Long) {
        try {
             usuarioRepository.deleteById(id)
        } catch (ex: Exception) {
            throw ex
        }
    }
}