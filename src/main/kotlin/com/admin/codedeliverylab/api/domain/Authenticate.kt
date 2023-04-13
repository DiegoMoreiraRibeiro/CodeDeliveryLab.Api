package com.admin.codedeliverylab.api.domain

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse

class Authenticate {

    fun isValid() : TokenResponse {
        return TokenResponse(
            token = "eu sou um token"
        )
    }

}