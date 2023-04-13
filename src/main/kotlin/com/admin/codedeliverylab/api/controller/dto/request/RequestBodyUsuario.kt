package com.admin.codedeliverylab.api.controller.dto.request

data class RequestBodyUsuario(
    val id: Long?,
    val nome: String,
    val email: String,
    val senha: String
)
