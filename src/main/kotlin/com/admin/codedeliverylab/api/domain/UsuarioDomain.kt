package com.admin.codedeliverylab.api.domain


import com.admin.codedeliverylab.api.entities.Usuario
import com.admin.codedeliverylab.api.repository.UsuarioRepository
import okhttp3.internal.wait
import org.springframework.data.repository.findByIdOrNull
import java.security.MessageDigest

class UsuarioDomain() {

    private lateinit var _usuario: Usuario
    private lateinit var _usuarioRepository: UsuarioRepository

    constructor(usuario: Usuario, usuarioRepository: UsuarioRepository) : this() {
        this._usuario = usuario
        this._usuarioRepository = usuarioRepository
    }

    fun preparaUsuario(): Usuario {
        try {
            // TODO: Criar validação de email

            if (_usuario.id == null) _usuario.senha = hashMD5(_usuario.senha)
            else {

                var usuario: Usuario? = _usuarioRepository.findByIdOrNull(_usuario.id!!) ?: throw Exception("Usuário não encontrado")

                if(usuario?.senha != _usuario.senha) _usuario.senha = hashMD5(_usuario.senha)

            }
            return _usuario
        } catch (ex: Exception) {
            throw ex
        }
    }

    private fun hashMD5(senha: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(senha.toByteArray())
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}