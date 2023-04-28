package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.entities.Pedido

interface PedidoService {
    fun savePedido(Pedido: Pedido): Pedido
    fun getPedidos(): List<Pedido>
    fun getPedidoById(id: Long): Pedido?
    fun deletePedido(id: Long)
}