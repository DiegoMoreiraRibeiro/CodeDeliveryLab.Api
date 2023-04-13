package com.admin.codedeliverylab.api.services.implementations

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyUsuario
import com.admin.codedeliverylab.api.controller.dto.response.UsuarioResponse
import com.admin.codedeliverylab.api.domain.UsuarioDomain
import com.admin.codedeliverylab.api.entities.Usuario
import com.admin.codedeliverylab.api.repository.UsuarioRepository
import com.admin.codedeliverylab.api.services.interfaces.IUsuarioService
import com.admin.codedeliverylab.api.utils.MapperHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) : IUsuarioService {


    override fun saveUsuario(usuario: RequestBodyUsuario): UsuarioResponse {
        try {
            var usuarioEntity = MapperHelper().mapObject(usuario, Usuario::class)
            usuarioEntity = UsuarioDomain(usuarioEntity, usuarioRepository).preparaUsuario()
            return MapperHelper().mapObject(usuarioRepository.save(usuarioEntity), UsuarioResponse::class)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun getUsuarios(): List<UsuarioResponse> {
        try {
            var listUsuario = usuarioRepository.findAll()
            return MapperHelper().mapList(listUsuario, UsuarioResponse::class)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun getUsuarioById(id: Long): UsuarioResponse {
        try {
            var usuario = usuarioRepository.findByIdOrNull(id) ?: throw Exception("Usuário não encotrado")
            return MapperHelper().mapObject(usuario, UsuarioResponse::class)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun deleteUsuario(id: Long) {
        try {
            MapperHelper().mapObject(usuarioRepository.deleteById(id), UsuarioResponse::class)
        } catch (ex: Exception) {
            throw ex
        }
    }
}