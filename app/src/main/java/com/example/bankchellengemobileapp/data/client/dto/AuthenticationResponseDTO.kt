package com.example.bankchellengemobileapp.data.client.dto

import java.util.UUID

data class AuthenticationResponseDTO(
    val token: String,
    val CLientUuid: UUID
)