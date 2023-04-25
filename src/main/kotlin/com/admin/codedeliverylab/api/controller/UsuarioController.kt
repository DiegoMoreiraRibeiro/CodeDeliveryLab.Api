package com.admin.codedeliverylab.api.controller

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyUsuario
import com.admin.codedeliverylab.api.controller.dto.response.UsuarioResponse
import com.admin.codedeliverylab.api.domain.UsuarioDomain
import com.admin.codedeliverylab.api.services.interfaces.AuthenticateServices
import com.admin.codedeliverylab.api.services.interfaces.UsuarioService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuario")
class UsuarioController(
    private val usuarioService: UsuarioService,
    private val authenticateServices: AuthenticateServices
) {

    private val usuarioDomain: UsuarioDomain = UsuarioDomain(usuarioService, authenticateServices)

    @PostMapping("")
    fun addUsuario(
        @RequestBody @Valid
        body: RequestBodyUsuario
    ): ResponseEntity<UsuarioResponse> {
        return try {
            ResponseEntity.ok().body(usuarioDomain.salvarUsuario(body))
        } catch (ex: Exception) {
            throw ex
        }
    }

    @PutMapping("")
    fun updateUsuario(
        @RequestBody @Valid
        body: RequestBodyUsuario
    ): ResponseEntity<UsuarioResponse> {
        return try {
            ResponseEntity.ok().body(usuarioDomain.salvarUsuario(body))
        } catch (ex: Exception) {
            throw ex
        }
    }

    @GetMapping("")
    fun get(
    ): ResponseEntity<List<UsuarioResponse>> {
        return try {
            ResponseEntity.ok().body(usuarioDomain.listarUsuarios())
        } catch (ex: Exception) {
            throw ex
        }
    }

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long,
    ): ResponseEntity<UsuarioResponse> {
        return try {
            ResponseEntity.ok().body(usuarioDomain.listarUsuario(id))
        } catch (ex: Exception) {
            throw ex
        }
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): String {
         try {
             usuarioDomain.removerUsuario(id)
             return ""
        } catch (ex: Exception) {
            throw ex
        }
    }

}