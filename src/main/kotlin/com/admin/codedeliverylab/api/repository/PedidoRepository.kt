package com.admin.codedeliverylab.api.repository

import com.admin.codedeliverylab.api.entities.Pedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoRepository : JpaRepository<Pedido, Long> {}
