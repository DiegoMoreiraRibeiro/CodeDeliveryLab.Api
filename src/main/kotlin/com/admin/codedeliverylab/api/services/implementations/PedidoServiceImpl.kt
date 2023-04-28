package com.admin.codedeliverylab.api.services.implementations

import com.admin.codedeliverylab.api.entities.Pedido
import com.admin.codedeliverylab.api.repository.PedidoRepository
import com.admin.codedeliverylab.api.services.interfaces.PedidoService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PedidoServiceImpl(private val pedidoRepository: PedidoRepository) : PedidoService {
    override fun savePedido(pedido: Pedido): Pedido {
        try {
            return pedidoRepository.save(pedido)
        } catch (ex: Exception) {
            throw Exception("Erro ao salvar pedidos - ${ex.message}")
        }
    }

    override fun getPedidos(): List<Pedido> {
        try {
            return pedidoRepository.findAll()
        } catch (ex: Exception) {
            throw Exception("Erro ao adicionar listar - ${ex.message}")
        }
    }

    override fun getPedidoById(id: Long): Pedido? {
        try {
            return pedidoRepository.findByIdOrNull(id)
        } catch (ex: Exception) {
            throw Exception("Erro ao listar pedido - ${ex.message}")
        }
    }

    override fun deletePedido(id: Long) {
        try {
            return pedidoRepository.deleteById(id)
        } catch (ex: Exception) {
            throw Exception("Erro ao listar deletePedido - ${ex.message}")
        }
    }
}