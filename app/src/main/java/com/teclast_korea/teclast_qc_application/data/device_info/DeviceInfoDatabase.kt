package com.teclast_korea.teclast_qc_application.data.device_info

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teclast_korea.teclast_qc_application.data.qc_result.local.DeviceSpec

@Database(
    entities = [DeviceSpec::class],
    version = 1
)
abstract  class DeviceSpecDatabase: RoomDatabase() {
    abstract val dao: DeviceSpecDao
}