package com.admin.codedeliverylab.api.domain

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse

class Authenticate {

    // TODO: Adicionar interface do repository no construtor

    fun isValid() : TokenResponse {
        return TokenResponse(
            token = "eu sou um token"
        )
    }

}