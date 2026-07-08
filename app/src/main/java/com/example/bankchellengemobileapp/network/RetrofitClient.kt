package com.example.bankchellengemobileapp.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

object RetrofitClient {

    private const val BASE_URL = "https://discern-carried-dweeb.ngrok-free.dev"

    private fun getRetrofit(context: Context): Retrofit {

        val authInterceptor = Interceptor { chain ->
            val token = TokenManager.getToken(context)
            val request = if (token != null) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                chain.request()
            }
            chain.proceed(request)
        }

        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, JsonDeserializer<LocalDate> { json, _, _ ->
                LocalDate.parse(json.asString)
            })
            .registerTypeAdapter(LocalDate::class.java, JsonSerializer<LocalDate> { src, _, _ ->
                JsonPrimitive(src.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            })
            .registerTypeAdapter(OffsetDateTime::class.java, JsonDeserializer<OffsetDateTime> { json, _, _ ->
                OffsetDateTime.parse(json.asString)
            })
            .registerTypeAdapter(OffsetDateTime::class.java, JsonSerializer<OffsetDateTime> { src, _, _ ->
                JsonPrimitive(src.toString())
            })
            .registerTypeAdapter(UUID::class.java, JsonDeserializer<UUID> { json, _, _ ->
                UUID.fromString(json.asString)
            })
            .registerTypeAdapter(UUID::class.java, JsonSerializer<UUID> { src, _, _ ->
                JsonPrimitive(src.toString())
            })
            .create()

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getClientApi(context: Context): ClientApiService =
        getRetrofit(context).create(ClientApiService::class.java)

    fun getClientLoginApi(context: Context): ClientLoginApiService =
        getRetrofit(context).create(ClientLoginApiService::class.java)

    fun getAccountApi(context: Context): AccountApiService =
        getRetrofit(context).create(AccountApiService::class.java)

}