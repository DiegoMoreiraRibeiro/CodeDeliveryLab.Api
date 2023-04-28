package com.admin.codedeliverylab.api.domain

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyPedido
import com.admin.codedeliverylab.api.controller.dto.response.DeleteResponse
import com.admin.codedeliverylab.api.controller.dto.response.PedidoResponse
import com.admin.codedeliverylab.api.entities.Pedido
import com.admin.codedeliverylab.api.services.interfaces.PedidoService
import com.admin.codedeliverylab.api.utils.mapList
import com.admin.codedeliverylab.api.utils.mapObject

class PedidoDomain(
        private val pedidoService: PedidoService,
) {

    private var pedido: Pedido? = null

    fun setPedido(pedido: Pedido) = pedido.also { this.pedido = it }

    fun salvarPedido(requestBodyPedido: RequestBodyPedido): PedidoResponse {
        try {
            val pedido = pedidoService.savePedido(mapObject(requestBodyPedido, Pedido::class))
            return mapObject(pedido, PedidoResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao salvarPedido")
        }
    }

    fun listarPedidos(): List<PedidoResponse> {
        try {
            return mapList(pedidoService.getPedidos(), PedidoResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao listarPedidos")
        }
    }

    fun listarPedido(id: Long): PedidoResponse {
        return try {
            val pedido: Pedido = pedidoService.getPedidoById(id) ?: throw Exception("Pedido não encontrado")
            return mapObject(pedido, PedidoResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao salvarPedido")
        }
    }

    fun removerPedido(id: Long): DeleteResponse {
        try {
            pedidoService.deletePedido(id)
            return DeleteResponse("Removido com sucesso")
        } catch (ex: Exception) {
            throw Exception("Erro ao removerPedido")
        }
    }
}