package com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TotalQCResult::class],
    version = 1,
    exportSchema = false
)
abstract class TestResultDatabase: RoomDatabase() {

    abstract val dao: TotalQCResultDao
}

