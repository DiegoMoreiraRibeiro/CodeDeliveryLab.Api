package com.admin.codedeliverylab.api.repository

import com.admin.codedeliverylab.api.entities.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<Cliente, Long> {}
