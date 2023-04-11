package com.admin.codedeliverylab.api.services.implementations

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse
import com.admin.codedeliverylab.api.domain.Authenticate
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import org.springframework.stereotype.Service

@Service
class AuthenticateImplementations : AuthenticateServices {

    override fun auth(): TokenResponse {
        return try {

            Authenticate().isValid()

        } catch (ex: Exception) {
            throw ex
        }
    }

}