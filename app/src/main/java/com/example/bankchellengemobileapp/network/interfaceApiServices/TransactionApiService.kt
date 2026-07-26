package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.transactions.dto.TimeFilterDTO
import com.example.bankchellengemobileapp.data.transactions.dto.TransactionRequestDTO
import com.example.bankchellengemobileapp.data.transactions.dto.TransactionResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface TransactionApiService {

    @POST("/api/v1/transacao/{accountId}")
    suspend fun transfer(
        @Path("accountId") accountId: UUID,
        @Body request: TransactionRequestDTO
    ): Response<TransactionResponseDTO>

    @GET("/api/v1/transacao/{accountId}/personalise")
    suspend fun getTransactionByPersonaliseTime(
        @Path("accountId") accountId: UUID,
        @Body filter: TimeFilterDTO
    ): Response<List<TransactionRequestDTO>>

}