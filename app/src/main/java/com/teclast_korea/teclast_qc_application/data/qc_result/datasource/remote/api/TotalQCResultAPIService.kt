package com.teclast_korea.teclast_qc_application.data.qc_result.datasource.remote.api

import retrofit2.http.POST

interface TotalQCResultAPIService {
    @POST("api/v1/qc_result")
    // want to make this post TotalQCResultAPIModel
    suspend fun postQCResult(totalQCResultAPIModel : TotalQCResultAPIModel): Long

}