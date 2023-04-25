package com.admin.codedeliverylab.api.services.interfaces

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

interface AuthenticateServices {

    fun gerarToken(usuario: UsernamePasswordAuthenticationToken): String
    fun getSubject(token: String?): String

}