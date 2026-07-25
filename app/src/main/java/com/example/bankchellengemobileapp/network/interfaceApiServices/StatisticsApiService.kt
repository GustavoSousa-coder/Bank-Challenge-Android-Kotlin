package com.example.bankchellengemobileapp.network.interfaceApiServices

import com.example.bankchellengemobileapp.data.statistics.dto.StatisticsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface StatisticsApiService {

    @GET("/api/v1/estatistica/{accountId}")
    suspend fun constructorStatistics(
        @Path("accountId") accountId: UUID,
        @Query("TimeSearch") timeSearch: Int? = 60
        ): Response<StatisticsResponseDTO>

}