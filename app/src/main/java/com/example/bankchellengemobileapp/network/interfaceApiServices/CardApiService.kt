package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.card.dto.CardResponseDTO
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CardApiService {

    @POST("/api/v1/card/{accountId}")
    suspend fun create(@Path("accountId") accountId: UUID): Response<CardResponseDTO>

}