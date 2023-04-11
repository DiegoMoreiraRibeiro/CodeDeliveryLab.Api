package com.admin.codedeliverylab.api.controller

import com.admin.codedeliverylab.api.controller.dto.response.TokenResponse
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/authenticate")
class AuthenticateController(private var authenticateService: AuthenticateServices) {

    @GetMapping("/auth")
    @ResponseBody
    fun getAuth(): TokenResponse {
        return authenticateService.auth();
    }

}