package com.admin.codedeliverylab.api.entities

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "senha")
    var senha: String
)
