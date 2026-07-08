package com.example.bankchellengemobileapp.data.client.dto

import com.example.bankchellengemobileapp.data.enums.ClientStatus
import java.time.LocalDate
import java.util.UUID

data class ClientResponseDTO(
    val uuid: UUID,
    val name: String,
    val dateOfBirth: LocalDate,
    val email: String,
    val clientStatus: ClientStatus,
)