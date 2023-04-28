package com.admin.codedeliverylab.api.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "pedido")
data class Pedido(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @NotBlank
        val descricao: String,

        @ManyToOne
        @JoinColumn(name = "cliente_id", nullable = false)
        val cliente: Cliente,

        )