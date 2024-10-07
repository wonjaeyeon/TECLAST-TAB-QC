package com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TotalQCResultDao {

    @Upsert
    suspend fun upsertTestResult(contact: TotalQCResult)

    @Delete
    suspend fun deleteTestResult(contact: TotalQCResult)

    @Query("DELETE FROM testResult")
    suspend fun deleteAllTestResults()

    @Query("SELECT * FROM testResult ORDER BY itemName ASC")
    fun getTestResultsOrderedByFirstName(): Flow<List<TotalQCResult>>

    @Query("SELECT * FROM testResult ORDER BY testResult ASC")
    fun getTestResultsOrderedByLastName(): Flow<List<TotalQCResult>>

    @Query("SELECT * FROM testResult ORDER BY testDate ASC")
    fun getTestResultsOrderedByPhoneNumber(): Flow<List<TotalQCResult>>

    @Query("SELECT COUNT(*) FROM testResult WHERE itemName = :itemName")
    suspend fun countTestResultByItemName(itemName: String): Int

    @Query("SELECT * FROM testResult WHERE itemName = :itemName LIMIT 1")
    suspend fun getTestResultByItemName(itemName: String): TotalQCResult?


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


