package com.teclast_korea.teclast_qc_application.data.device_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.teclast_korea.teclast_qc_application.data.qc_result.local.DeviceSpec

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