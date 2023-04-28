package com.admin.codedeliverylab.api.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "cliente")
data class Cliente(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @NotBlank
        val nome: String,

        @NotBlank
        val email: String,

        @NotBlank
        val telefone: String,

        )