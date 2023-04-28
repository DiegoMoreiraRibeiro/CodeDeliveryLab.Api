package com.admin.codedeliverylab.api.repository

import com.admin.codedeliverylab.api.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email: String): Usuario
    fun findByEmailAndSenha(email: String, senha: String): Usuario
}
