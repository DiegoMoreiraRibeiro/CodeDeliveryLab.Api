package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse


interface IAuthenticateServices {
    fun auth() : TokenResponse

}