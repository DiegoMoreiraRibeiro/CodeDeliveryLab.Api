package com.admin.codedeliverylab.api.controller.dto.response

data class UsuarioResponse(
    val id: Long,

    val nome: String,

    val email: String,

    val senha: String
)