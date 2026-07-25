package com.example.bankchellengemobileapp.data.pixKey.dto

import com.example.bankchellengemobileapp.data.enums.KeyType

data class PixTransferResponseDTO(

    val keyType: KeyType,
    val keyValue: String

)
