package com.example.bankchellengemobileapp.data.pixKey.dto

import com.example.bankchellengemobileapp.data.enums.KeyType
import java.util.UUID

data class PixTransferRequestDTO(

    val keyType: KeyType,
    val keyValue: String,
    val accountId: UUID

)
