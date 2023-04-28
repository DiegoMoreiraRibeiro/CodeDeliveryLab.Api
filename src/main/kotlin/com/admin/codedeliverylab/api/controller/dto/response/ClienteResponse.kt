package com.admin.codedeliverylab.api.controller.dto.response


data class ClienteResponse(
        val id: Long = 0,
        val nome: String,
        val email: String,
        val telefone: String,
)