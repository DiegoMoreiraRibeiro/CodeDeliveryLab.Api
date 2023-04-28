package com.admin.codedeliverylab.api.controller.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class UsuarioResponse(
    val id: Long? = null,
    val nome: String,
    val role: String,
    val email: String,
)