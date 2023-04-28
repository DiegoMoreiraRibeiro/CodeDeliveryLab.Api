package com.admin.codedeliverylab.api.controller


import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyCliente
import com.admin.codedeliverylab.api.controller.dto.response.ClienteResponse
import com.admin.codedeliverylab.api.controller.dto.response.DeleteResponse
import com.admin.codedeliverylab.api.domain.ClienteDomain
import com.admin.codedeliverylab.api.services.interfaces.ClienteService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cliente")
class ClienteController(
        private val clienteService: ClienteService,
) {

    private val clienteDomain: ClienteDomain = ClienteDomain(clienteService)

    @PostMapping("")
    fun addCliente(
            @RequestBody @Valid
            body: RequestBodyCliente,
    ): ResponseEntity<ClienteResponse> = ResponseEntity.ok().body(clienteDomain.salvarCliente(body))


    @PutMapping("")
    fun updateCliente(
            @RequestBody @Valid
            body: RequestBodyCliente,
    ): ResponseEntity<ClienteResponse> = ResponseEntity.ok().body(clienteDomain.salvarCliente(body))

    @GetMapping("")
    fun get(
    ): ResponseEntity<List<ClienteResponse>> = ResponseEntity.ok().body(clienteDomain.listarClientes())

    @GetMapping("/{id}")
    fun getById(
            @PathVariable id: Long,
    ): ResponseEntity<ClienteResponse> = ResponseEntity.ok().body(clienteDomain.listarCliente(id))

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable id: Long,
    ): ResponseEntity<DeleteResponse> = ResponseEntity.ok().body(clienteDomain.removerCliente(id))


}