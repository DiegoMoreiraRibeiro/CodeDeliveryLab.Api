package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.entities.Usuario

interface UsuarioService {
    fun validateLoginUsuario(email: String, senha: String): Usuario?
    fun saveUsuario(usuario: Usuario): Usuario

    fun getUsuarios(): List<Usuario>

    fun getUsuarioById(id: Long): Usuario?

    fun deleteUsuario(id: Long)
}