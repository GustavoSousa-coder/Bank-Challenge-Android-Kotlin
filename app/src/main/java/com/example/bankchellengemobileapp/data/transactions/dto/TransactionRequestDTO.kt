package com.example.bankchellengemobileapp.data.transactions.dto

import com.example.bankchellengemobileapp.data.card.dto.CardTransactionRequestDTO
import com.example.bankchellengemobileapp.data.enums.TransactionMethod
import java.math.BigDecimal

data class TransactionRequestDTO(

    val amount: BigDecimal,
    val key: String,
    val method: TransactionMethod,
    val cardDTO: CardTransactionRequestDTO

)
