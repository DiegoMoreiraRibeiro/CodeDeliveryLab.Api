package com.admin.codedeliverylab.api.services.implementations

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse
import com.admin.codedeliverylab.api.domain.Authenticate
import com.admin.codedeliverylab.api.services.interfaces.IAuthenticateServices
import org.springframework.stereotype.Service

@Service
class AuthenticateImplementations : IAuthenticateServices {

    override fun auth(): TokenResponse {
        return try {

            Authenticate().isValid()

        } catch (ex: Exception) {
            throw ex
        }
    }

}