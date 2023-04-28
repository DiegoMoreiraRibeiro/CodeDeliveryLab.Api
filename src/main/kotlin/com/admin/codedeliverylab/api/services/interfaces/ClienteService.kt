package com.admin.codedeliverylab.api.services.interfaces

import com.admin.codedeliverylab.api.entities.Cliente

interface ClienteService {
    fun saveCliente(Cliente: Cliente): Cliente
    fun getClientes(): List<Cliente>
    fun getClienteById(id: Long): Cliente?
    fun deleteCliente(id: Long)
}