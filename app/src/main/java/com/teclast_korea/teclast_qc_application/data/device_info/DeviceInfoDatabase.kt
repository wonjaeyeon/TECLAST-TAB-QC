package com.teclast_korea.teclast_qc_application.data.device_info

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DeviceSpec::class],
    version = 1,
    exportSchema = false
)
abstract  class DeviceSpecDatabase: RoomDatabase() {
    abstract val dao: DeviceSpecDao
}