package com.example.teclast_qc_application.test_result.test_results_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TestResult::class],
    version = 1
)
abstract class TestResultDatabase: RoomDatabase() {

    abstract val dao: TestResultDao
}

@Database(
    entities = [DeviceSpec::class],
    version = 1
)
abstract  class DeviceSpecDatabase: RoomDatabase() {
    abstract val dao: DeviceSpecDao
}