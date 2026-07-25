package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.account.dto.AccountResponseDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface AccountApiService {

    @GET("/api/v1/account/{clientUuid}/accounts")
    suspend fun getAllByClientId(@Path("clientUuid") clientUuid: UUID): Response<List<AccountResponseDTO>>

    @GET("/api/v1/account/{uuid}/account")
    suspend fun getById(@Path("uuid") uuid: UUID): Response<AccountResponseDTO>

    @POST("/api/v1/account/{clientUuid}")
    suspend fun save(@Path("clientUuid") clientUuid: UUID): Response<AccountResponseDTO>

    @DELETE("/api/v1/account/{uuid}")
    suspend fun delete(@Path("uuid") uuid: UUID): Response<Unit>
}