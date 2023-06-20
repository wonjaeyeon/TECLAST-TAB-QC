package com.example.teclast_qc_application.test_result.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_bookmark")
data class ParkBookmarkEntity(
    @PrimaryKey
    val parkingCode: String = "",
    val parkingName: String = "",
    val addr: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val tel: String = "",
    val operation_rule_nm: String = "",
    var isSelected:Boolean = false
)