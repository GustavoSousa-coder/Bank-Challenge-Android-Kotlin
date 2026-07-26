package com.example.bankchellengemobileapp.data.transactions.dto

import java.math.BigDecimal
import java.time.OffsetDateTime

data class TransactionResponseDTO(

    val amount: BigDecimal,
    val dataHora: OffsetDateTime

)
