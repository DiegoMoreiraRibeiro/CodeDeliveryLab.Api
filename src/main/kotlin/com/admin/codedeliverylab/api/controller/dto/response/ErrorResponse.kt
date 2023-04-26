package com.admin.codedeliverylab.api.controller.dto.response

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val message: String,
    val status: HttpStatus
)