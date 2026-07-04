package com.example.bankchellengemobileapp.network

import com.example.bankchellengemobileapp.data.client.dto.ClientRequestDTO
import com.example.bankchellengemobileapp.data.client.dto.ClientResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface ClientApiService {

    @GET("/api/v1/client")
    suspend fun gelAll(): Response<List<ClientResponseDTO>>

    @GET("/api/v1/client/{uuid}")
    suspend fun getById(@Path("uuid") uuid: UUID): Response<ClientResponseDTO>

    @POST("/api/v1/client")
    suspend fun save(@Body request: ClientRequestDTO): ClientResponseDTO

    @DELETE("/api/v1/client/{uuid}")
    suspend fun delete(@Path("uuid") uuid: UUID): Response<Unit>

}