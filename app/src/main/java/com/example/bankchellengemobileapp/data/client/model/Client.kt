package com.example.bankchellengemobileapp.data.client.model

import com.example.bankchellengemobileapp.data.account.model.Account
import com.example.bankchellengemobileapp.data.enums.ClientRoles
import com.example.bankchellengemobileapp.data.enums.ClientStatus
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

data class Client(
    val uuid: UUID,
    val cpf: String,
    val name: String,
    val dateOfBirth: LocalDate,
    val email: String,
    val password: String,
    val clientStatus: ClientStatus,
    val createdAt: OffsetDateTime,
    val accounts: List<Account>,
    val role: ClientRoles

)
