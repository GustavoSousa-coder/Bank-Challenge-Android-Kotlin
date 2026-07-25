package com.example.bankchellengemobileapp.data.card.dto

import com.example.bankchellengemobileapp.data.enums.CardStatus
import java.time.OffsetDateTime
import java.time.YearMonth
import java.util.UUID

data class CardResponseDTO(

    val uuid: UUID,
    val cardNumber: String,
    val holderName: String,
    val expirationDate: YearMonth,
    val status: CardStatus,
    val createdAt: OffsetDateTime

)
