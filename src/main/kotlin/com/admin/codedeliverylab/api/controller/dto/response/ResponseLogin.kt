package com.admin.codedeliverylab.api.controller.dto.response

import org.springframework.http.ResponseEntity


sealed class ResponseLogin {
    data class Success(val token: String) : ResponseLogin()
    data class Error(val status: String, val  message: String) : ResponseLogin()

}



