package com.example.teclast_qc_application.test_result.device_spec_db//package com.example.teclast_qc_application.test_result.room_db
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Query
//import androidx.room.Upsert
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ContactDao2 {
//
//    @Upsert
//    suspend fun upsertContact(contact: Contact)
//
//    @Delete
//    suspend fun deleteContact(contact: Contact)
//
//    @Query("SELECT * FROM contact ORDER BY firstName ASC")
//    fun getContactsOrderedByFirstName(): Flow<List<Contact>>
//
//    @Query("SELECT * FROM contact ORDER BY lastName ASC")
//    fun getContactsOrderedByLastName(): Flow<List<Contact>>
//
//    @Query("SELECT * FROM contact ORDER BY phoneNumber ASC")
//    fun getContactsOrderedByPhoneNumber(): Flow<List<Contact>>
//}