package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.card.dto.CardResponseDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CardApiService {

    @GET("/api/v1/card/{accountId}/find")
    suspend fun findAll(@Path("accountId") accountId: UUID): Response<List<CardResponseDTO>>

    @POST("/api/v1/card/{accountId}")
    suspend fun create(@Path("accountId") accountId: UUID): Response<CardResponseDTO>

    @DELETE("/api/v1/card/{accountId}/delete")
    suspend fun delete(@Path("accountId") accountId: UUID): Response<Unit>

}