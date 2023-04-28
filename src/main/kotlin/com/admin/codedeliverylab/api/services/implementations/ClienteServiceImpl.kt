package com.admin.codedeliverylab.api.services.implementations

import com.admin.codedeliverylab.api.entities.Cliente
import com.admin.codedeliverylab.api.repository.ClienteRepository
import com.admin.codedeliverylab.api.services.interfaces.ClienteService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ClienteServiceImpl(private val clienteRepository: ClienteRepository) : ClienteService {
    override fun saveCliente(cliente: Cliente): Cliente {
        try {
            return clienteRepository.save(cliente)
        } catch (ex: Exception) {
            throw Exception("Erro ao salvar saveCliente - ${ex.message}")
        }
    }

    override fun getClientes(): List<Cliente> {
        try {
            return clienteRepository.findAll()
        } catch (ex: Exception) {
            throw Exception("Erro ao getClientes - ${ex.message}")
        }
    }

    override fun getClienteById(id: Long): Cliente? {
        try {
            return clienteRepository.findByIdOrNull(id)
        } catch (ex: Exception) {
            throw Exception("Erro ao listar Cliente - ${ex.message}")
        }
    }

    override fun deleteCliente(id: Long) {
        try {
            return clienteRepository.deleteById(id)
        } catch (ex: Exception) {
            throw Exception("Erro ao listar deletePedido - ${ex.message}")
        }
    }
}