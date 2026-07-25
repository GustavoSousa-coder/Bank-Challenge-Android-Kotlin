package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.client.dto.AuthenticationRequestDTO
import com.example.bankchellengemobileapp.data.client.dto.AuthenticationResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientLoginApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body auth: AuthenticationRequestDTO): Response<AuthenticationResponseDTO>

}