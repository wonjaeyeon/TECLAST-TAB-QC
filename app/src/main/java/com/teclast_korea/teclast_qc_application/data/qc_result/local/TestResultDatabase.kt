package com.teclast_korea.teclast_qc_application.data.qc_result.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TestResult::class],
    version = 1
)
abstract class TestResultDatabase: RoomDatabase() {

    abstract val dao: TestResultDao
}

