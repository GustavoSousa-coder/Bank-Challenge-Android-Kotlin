package com.example.bankchellengemobileapp.data.statistics.dto

import java.math.BigDecimal

data class StatisticsResponseDTO(

    val count: Long,
    val sum: BigDecimal,
    val avg: BigDecimal,
    val min: BigDecimal,
    val max: BigDecimal

)
