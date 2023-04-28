package com.admin.codedeliverylab.api.controller.dto.request


data class RequestBodyCliente(
        val id: Long = 0,
        val nome: String,
        val email: String,
        val telefone: String,
)