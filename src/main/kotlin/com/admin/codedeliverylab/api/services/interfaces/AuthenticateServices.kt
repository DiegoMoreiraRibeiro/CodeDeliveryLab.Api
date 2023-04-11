package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse


interface AuthenticateServices {
    fun auth() : TokenResponse

}