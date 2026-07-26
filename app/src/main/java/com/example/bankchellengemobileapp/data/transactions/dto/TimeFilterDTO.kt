package com.example.bankchellengemobileapp.data.transactions.dto

import com.example.bankchellengemobileapp.data.enums.TimeUnitType
import java.time.ZoneId

data class TimeFilterDTO(

    val type: TimeUnitType,
    val amount: Long,
    val zone: ZoneId,
    val dateIso: String

    )
