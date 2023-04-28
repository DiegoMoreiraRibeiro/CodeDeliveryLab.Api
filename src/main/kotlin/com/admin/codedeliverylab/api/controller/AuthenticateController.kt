package com.admin.codedeliverylab.api.controller

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyLogin
import com.admin.codedeliverylab.api.controller.dto.response.ResponseLogin
import com.admin.codedeliverylab.api.domain.UsuarioDomain
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import com.admin.codedeliverylab.api.services.interfaces.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/authenticate")
class AuthenticateController(
        private val usuarioService: UsuarioService,
        private val authenticateServices: AuthenticateServices,
) {

    private val usuarioDomain: UsuarioDomain = UsuarioDomain(usuarioService, authenticateServices)

    @PostMapping("/login")
    @ResponseBody
    fun getAuth(
            @RequestBody requestBodyLogin: RequestBodyLogin,
    ): ResponseEntity<ResponseLogin> {
        return try {
            ResponseEntity.ok(
                    ResponseLogin.Success(
                            usuarioDomain.validarLogin(requestBodyLogin.email, requestBodyLogin.senha)
                    )
            )
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ex.message?.let { ResponseLogin.Error(HttpStatus.UNAUTHORIZED.toString(), it) }
            )
        }
    }

}