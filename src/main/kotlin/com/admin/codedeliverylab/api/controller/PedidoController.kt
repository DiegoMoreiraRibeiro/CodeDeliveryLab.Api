package com.admin.codedeliverylab.api.controller


import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyPedido
import com.admin.codedeliverylab.api.controller.dto.response.DeleteResponse
import com.admin.codedeliverylab.api.controller.dto.response.PedidoResponse
import com.admin.codedeliverylab.api.domain.PedidoDomain
import com.admin.codedeliverylab.api.services.interfaces.PedidoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pedido")
class PedidoController(
        private val pedidoService: PedidoService,
) {

    private val pedidoDomain: PedidoDomain = PedidoDomain(pedidoService)

    @PostMapping("")
    fun addPedido(
            @RequestBody @Valid
            body: RequestBodyPedido,
    ): ResponseEntity<PedidoResponse> = ResponseEntity.ok().body(pedidoDomain.salvarPedido(body))


    @PutMapping("")
    fun updatePedido(
            @RequestBody @Valid
            body: RequestBodyPedido,
    ): ResponseEntity<PedidoResponse> = ResponseEntity.ok().body(pedidoDomain.salvarPedido(body))

    @GetMapping()
    fun get(
    ): ResponseEntity<List<PedidoResponse>> = ResponseEntity.ok().body(pedidoDomain.listarPedidos())

    @GetMapping("/{id}")
    fun getById(
            @PathVariable id: Long,
    ): ResponseEntity<PedidoResponse> = ResponseEntity.ok().body(pedidoDomain.listarPedido(id))

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable id: Long,
    ): ResponseEntity<DeleteResponse> = ResponseEntity.ok().body(pedidoDomain.removerPedido(id))


}