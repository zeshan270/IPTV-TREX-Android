package com.iptv_trex.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var apiService: ApiService? = null

    fun getApiService(baseUrl: String): ApiService {
        return apiService ?: createRetrofitClient(baseUrl).create(ApiService::class.java).also {
            apiService = it
        }
    }

    fun resetApiService() {
        apiService = null
    }

    private fun createRetrofitClient(baseUrl: String): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}
