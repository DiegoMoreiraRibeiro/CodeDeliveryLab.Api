package com.admin.codedeliverylab.api.controller.dto.response

import com.admin.codedeliverylab.api.entities.Cliente


data class PedidoResponse(
        val id: Long = 0,
        val descricao: String,
        val cliente: Cliente,
)