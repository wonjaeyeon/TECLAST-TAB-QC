package com.example.teclast_qc_application.test_result.test_results_db

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

@Entity(tableName = "DeviceSpec")
data class DeviceSpec(
    val deviceItem : String,
    val SpecValue : String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)