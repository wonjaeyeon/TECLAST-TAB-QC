package com.example.teclast_qc_application.test_result.test_results_db

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

    @Query("SELECT * FROM testResult ORDER BY itemName ASC")
    fun getTestResultsOrderedByFirstName(): Flow<List<TestResult>>

    @Query("SELECT * FROM testResult ORDER BY testResult ASC")
    fun getTestResultsOrderedByLastName(): Flow<List<TestResult>>

    @Query("SELECT * FROM testResult ORDER BY testDate ASC")
    fun getTestResultsOrderedByPhoneNumber(): Flow<List<TestResult>>

    @Query("SELECT COUNT(*) FROM testResult WHERE itemName = :itemName")
    suspend fun countTestResultByItemName(itemName: String): Int

    @Query("SELECT * FROM testResult WHERE itemName = :itemName LIMIT 1")
    suspend fun getTestResultByItemName(itemName: String): TestResult
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