package com.admin.codedeliverylab.api.domain

import com.admin.codedeliverylab.api.controller.dto.request.RequestBodyCliente
import com.admin.codedeliverylab.api.controller.dto.response.ClienteResponse
import com.admin.codedeliverylab.api.controller.dto.response.DeleteResponse
import com.admin.codedeliverylab.api.entities.Cliente
import com.admin.codedeliverylab.api.services.interfaces.ClienteService
import com.admin.codedeliverylab.api.utils.mapList
import com.admin.codedeliverylab.api.utils.mapObject

class ClienteDomain(
        private val clienteService: ClienteService,
) {

    private var cliente: Cliente? = null

    fun setCliente(cliente: Cliente) = cliente.also { this.cliente = it }

    fun salvarCliente(requestBodyCliente: RequestBodyCliente): ClienteResponse {
        try {
            val cliente = clienteService.saveCliente(mapObject(requestBodyCliente, Cliente::class))
            return mapObject(cliente, ClienteResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao salvarCliente")
        }
    }

    fun listarClientes(): List<ClienteResponse> {
        try {
            return mapList(clienteService.getClientes(), ClienteResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao listarClientes")
        }
    }

    fun listarCliente(id: Long): ClienteResponse {
        return try {
            val cliente: Cliente = clienteService.getClienteById(id) ?: throw Exception("Cliente não encontrado")
            return mapObject(cliente, ClienteResponse::class)
        } catch (ex: Exception) {
            throw Exception("Erro ao listarCliente")
        }
    }

    fun removerCliente(id: Long): DeleteResponse {
        try {
            clienteService.deleteCliente(id)
            return DeleteResponse("Removido com sucesso")
        } catch (ex: Exception) {
            throw Exception("Erro ao removerCliente")
        }
    }
}