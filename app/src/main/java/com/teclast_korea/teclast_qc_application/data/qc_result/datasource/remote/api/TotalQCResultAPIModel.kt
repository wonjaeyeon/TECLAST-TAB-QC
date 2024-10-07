package com.teclast_korea.teclast_qc_application.data.qc_result.datasource.remote.api

import com.google.gson.annotations.SerializedName
import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.TotalQCResult

data class TotalQCResultAPIModel(
    @SerializedName("order_id")
    val orderId: String,

    @SerializedName("tablet_id")
    val tabletId: String,

    @SerializedName("test_result")
    val totalQCResult: List<TotalQCResult>,

    @SerializedName("test_status")
    val testStatus: String


    )
