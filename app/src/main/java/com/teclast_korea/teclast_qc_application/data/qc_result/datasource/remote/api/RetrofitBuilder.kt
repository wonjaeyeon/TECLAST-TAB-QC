package com.teclast_korea.teclast_qc_application.data.qc_result.datasource.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://________" // secure to public

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val totalQCResultApiService: TotalQCResultAPIService = buildRetrofit().create(TotalQCResultAPIService::class.java)
}