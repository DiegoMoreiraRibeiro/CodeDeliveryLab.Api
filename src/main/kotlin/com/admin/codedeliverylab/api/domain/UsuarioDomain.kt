package com.admin.codedeliverylab.api.domain


import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyUsuario
import com.admin.codedeliverylab.api.controller.dto.response.UsuarioResponse
import com.admin.codedeliverylab.api.entities.Usuario
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import com.admin.codedeliverylab.api.services.interfaces.UsuarioService
import com.admin.codedeliverylab.api.utils.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.security.MessageDigest

class UsuarioDomain() {

    private var usuario: Usuario? = null
    private lateinit var usuarioService: UsuarioService
    private lateinit var authenticateServices: AuthenticateServices

    constructor(usuarioService: UsuarioService, authenticateServices: AuthenticateServices) : this() {
        this.usuarioService = usuarioService
        this.authenticateServices = authenticateServices
    }

    fun setUsuario(usuario: Usuario) = usuario.also { this.usuario = it }


    fun salvarUsuario(requestBodyUsuario: RequestBodyUsuario): UsuarioResponse {
        try {
            val usuarioEntity = preparaUsuario(mapObject(requestBodyUsuario, Usuario::class))
            return mapObject(usuarioEntity, UsuarioResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao salvarUsuario")
        }
    }

    fun listarUsuarios(): List<UsuarioResponse> {
        try {
            return mapList(usuarioService.getUsuarios(), UsuarioResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao listarUsuarios")
        }
    }

    fun listarUsuario(id: Long): UsuarioResponse {
        return try {
            var teste: Usuario? = usuarioService.getUsuarioById(id) ?: throw Exception("Usuário não encontrado")
            UsuarioResponse(
                id = teste?.id,
                nome = teste!!.nome,
                login = teste.login,
                role = teste.role,
                email = teste.email,
                senha = teste.senha
            )
        } catch (ex: Exception) {
            throw Exception("Erro ao salvarUsuario")
        }
    }

    private fun preparaUsuario(usuario: Usuario): Usuario {
        try {
            if (usuario.id == null) usuario.senha = hashMD5(usuario.senha)
            else {
                var u = usuarioService.getUsuarioById(usuario.id) ?: throw Exception("Usuário não encontrado")
                if (u.senha != usuario.senha) usuario.senha = hashMD5(usuario.senha)
            }
            return usuario
        } catch (ex: Exception) {
            throw Exception("Erro ao preparaUsuario")
        }
    }

    fun validarLogin(email: String, senha: String): String {
        try {
            val usuario = usuarioService.validateLoginUsuario(email, hashMD5(senha)) ?: throw Exception()
            val authorities: Collection<GrantedAuthority> =
                listOf(SimpleGrantedAuthority(usuario.role))

            return authenticateServices.gerarToken(
                UsernamePasswordAuthenticationToken(
                    email,
                    senha, authorities
                )
            )

        } catch (ex: Exception) {
            throw Exception("Não validarLogin")
        }
    }

    fun removerUsuario(id: Long): String {
        try {
            usuarioService.deleteUsuario(id)
            return "Removido com sucesso"
        } catch (ex: Exception) {
            throw Exception("Erro ao removerUsuario")
        }
    }

    private fun hashMD5(senha: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(senha.toByteArray())
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}