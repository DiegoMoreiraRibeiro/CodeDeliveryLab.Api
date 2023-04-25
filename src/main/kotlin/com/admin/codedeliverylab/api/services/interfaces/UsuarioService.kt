package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyUsuario
import com.admin.codedeliverylab.api.entities.Usuario
import org.springframework.stereotype.Component

interface UsuarioService {
    fun validateLoginUsuario(login: String, senha: String): Usuario?
    fun saveUsuario(usuario: Usuario): Usuario

    fun getUsuarios() : List<Usuario>

    fun getUsuarioById(id: Long) : Usuario?

    fun deleteUsuario(id: Long)
}