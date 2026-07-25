package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.pixKey.dto.PixTransferRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface PixKeyApiService {

    @POST("/api/v1/pix/{accountId}")
    suspend fun registerAddressKey(@Body request: PixTransferRequestDTO, @Path("accountId") accountId: UUID): Response<Any>

}