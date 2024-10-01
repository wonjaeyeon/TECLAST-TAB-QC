package com.teclast_korea.teclast_qc_application.data.qc_result.local

import androidx.room.Entity
import androidx.room.PrimaryKey


// DB를 어떻게 정의할 것인가?
@Entity(tableName = "TestResult")
data class TestResult(
    val itemName: String,
    val testResult: String,
    val testDate: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

