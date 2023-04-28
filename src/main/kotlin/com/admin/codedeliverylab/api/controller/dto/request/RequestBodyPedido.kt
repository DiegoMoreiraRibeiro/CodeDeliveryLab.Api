package com.admin.codedeliverylab.api.controller.dto.request

import com.admin.codedeliverylab.api.entities.Cliente


data class RequestBodyPedido(
        val id: Long = 0,
        val descricao: String,
        val cliente: Cliente,
)