package com.admin.codedeliverylab.api.domain


import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyUsuario
import com.admin.codedeliverylab.api.controller.dto.response.UsuarioResponse
import com.admin.codedeliverylab.api.entities.Usuario
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import com.admin.codedeliverylab.api.services.interfaces.UsuarioService
import com.admin.codedeliverylab.api.utils.hashMD5
import com.admin.codedeliverylab.api.utils.mapList
import com.admin.codedeliverylab.api.utils.mapObject
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class UsuarioDomain(
        private val usuarioService: UsuarioService,
        private val authenticateServices: AuthenticateServices,
) {

    private var usuario: Usuario? = null
    fun setUsuario(usuario: Usuario) = usuario.also { this.usuario = it }

    fun salvarUsuario(requestBodyUsuario: RequestBodyUsuario): UsuarioResponse {

        val usuarioEntity = preparaUsuario(mapObject(requestBodyUsuario, Usuario::class))
        return mapObject(usuarioService.saveUsuario(usuarioEntity), UsuarioResponse::class)

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
                    role = teste.role,
                    email = teste.email,
            )
        } catch (ex: Exception) {
            throw Exception("Erro ao salvarUsuario")
        }
    }

    private fun preparaUsuario(usuario: Usuario): Usuario {
        try {
            if (usuario.id == null || usuario.id.toInt() == 0) usuario.senha = hashMD5(usuario.senha)
            else {
                val u = usuarioService.getUsuarioById(usuario.id) ?: throw Exception("Usuário não encontrado")
                if (usuario.senha != "" && u.senha != usuario.senha) usuario.senha = hashMD5(usuario.senha)
                else usuario.senha = u.senha
            }
            usuario.role = "ADMIN"
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
            throw Exception("Não autorizado")
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


}