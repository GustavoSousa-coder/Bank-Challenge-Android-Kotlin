package com.example.bankchellengemobileapp.data.account.dto

import com.example.bankchellengemobileapp.data.enums.AccountStatus
import com.example.bankchellengemobileapp.data.enums.AccountType
import java.math.BigDecimal
import java.util.UUID

data class AccountResponseDTO(
    val uuid: UUID,
    val clientUuid: UUID,
    val balance: BigDecimal,
    val type: AccountType,
    val overdraftLimit: BigDecimal,
    val status: AccountStatus
)