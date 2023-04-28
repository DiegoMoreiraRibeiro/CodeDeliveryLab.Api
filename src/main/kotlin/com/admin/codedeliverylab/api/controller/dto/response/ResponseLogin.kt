package com.admin.codedeliverylab.api.controller.dto.response


sealed class ResponseLogin {
    data class Success(val token: String) : ResponseLogin()
    data class Error(val status: String, val message: String) : ResponseLogin()

}



