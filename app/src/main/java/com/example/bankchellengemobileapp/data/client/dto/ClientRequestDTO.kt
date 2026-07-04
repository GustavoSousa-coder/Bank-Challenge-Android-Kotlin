package com.example.bankchellengemobileapp.data.client.dto

import java.time.LocalDate

data class ClientRequestDTO(
    val name: String,
    val cpf: String,
    val dateOfBirth: LocalDate,
    val email: String,
    val password: String
)