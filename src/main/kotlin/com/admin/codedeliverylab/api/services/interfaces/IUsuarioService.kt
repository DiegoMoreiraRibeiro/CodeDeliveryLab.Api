package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyUsuario
import com.admin.codedeliverylab.api.controller.dto.response.UsuarioResponse

interface IUsuarioService {

    fun saveUsuario(usuario: RequestBodyUsuario): UsuarioResponse

    fun getUsuarios() : List<UsuarioResponse>

    fun getUsuarioById(id: Long) : UsuarioResponse

    fun deleteUsuario(id: Long)
}