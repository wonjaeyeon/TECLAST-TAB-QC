package com.teclast_korea.teclast_qc_application.data.device_info

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DeviceSpec")
data class DeviceSpec(
    val deviceItem : String,
    val SpecValue : String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)