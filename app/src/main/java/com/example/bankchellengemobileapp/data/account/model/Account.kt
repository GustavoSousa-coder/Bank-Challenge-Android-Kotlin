package com.example.bankchellengemobileapp.data.account.model

import com.example.bankchellengemobileapp.data.enums.AccountStatus
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

data class Account(
    var uuid: UUID? = null,
    var accountNumber: String = "5706",
    var agencyNumber: String = "0001",
    var balance: BigDecimal = BigDecimal.ZERO,
    var overdraftLimit: BigDecimal = BigDecimal.valueOf(1000),
    var status: AccountStatus = AccountStatus.ACTIVE,
    var createdAt: OffsetDateTime,
    var withdrawalCount: Int = 0,
    var lastWithdrawalReset: LocalDate
)
