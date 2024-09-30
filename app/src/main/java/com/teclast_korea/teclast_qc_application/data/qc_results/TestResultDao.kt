package com.teclast_korea.teclast_qc_application.data.qc_results

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TestResultDao {

    @Upsert
    suspend fun upsertTestResult(contact: TestResult)

    @Delete
    suspend fun deleteTestResult(contact: TestResult)

    @Query("DELETE FROM testResult")
    suspend fun deleteAllTestResults()

    @Query("SELECT * FROM testResult ORDER BY itemName ASC")
    fun getTestResultsOrderedByFirstName(): Flow<List<TestResult>>

    @Query("SELECT * FROM testResult ORDER BY testResult ASC")
    fun getTestResultsOrderedByLastName(): Flow<List<TestResult>>

    @Query("SELECT * FROM testResult ORDER BY testDate ASC")
    fun getTestResultsOrderedByPhoneNumber(): Flow<List<TestResult>>

    @Query("SELECT COUNT(*) FROM testResult WHERE itemName = :itemName")
    suspend fun countTestResultByItemName(itemName: String): Int

    @Query("SELECT * FROM testResult WHERE itemName = :itemName LIMIT 1")
    suspend fun getTestResultByItemName(itemName: String): TestResult?


    @Query("SELECT itemName FROM testResult GROUP BY itemName HAVING COUNT(*) >= 2")
    suspend fun getItemNamesWithMoreThanTwoEntries(): List<String>

    @Query("SELECT id FROM testResult WHERE itemName = :itemName ORDER BY testDate DESC LIMIT -1 OFFSET 1")
    suspend fun getIdsForDeletion(itemName: String): List<Long>

    @Query("DELETE FROM testResult WHERE id IN (:ids)")
    suspend fun deleteTestResultsByIds(ids: List<Long>)

    suspend fun cleanUpPreviousTestResults() {
        val itemNames = getItemNamesWithMoreThanTwoEntries()
        itemNames.forEach { itemName ->
            val idsToDelete = getIdsForDeletion(itemName)
            if (idsToDelete.isNotEmpty()) {
                deleteTestResultsByIds(idsToDelete)
            }
        }
    }
}


@Dao
interface DeviceSpecDao {

    @Upsert
    suspend fun upsertTestResult(contact: DeviceSpec)

    @Delete
    suspend fun deleteTestResult(contact: DeviceSpec)

//    @Query("SELECT * FROM deviceSpec ORDER BY firstName ASC")
//    fun getTestResultsOrderedByFirstName(): Flow<List<DeviceSpec>>
//
//    @Query("SELECT * FROM deviceSpec ORDER BY lastName ASC")
//    fun getTestResultsOrderedByLastName(): Flow<List<DeviceSpec>>
//
//    @Query("SELECT * FROM deviceSpec ORDER BY phoneNumber ASC")
//    fun getTestResultsOrderedByPhoneNumber(): Flow<List<DeviceSpec>>
}