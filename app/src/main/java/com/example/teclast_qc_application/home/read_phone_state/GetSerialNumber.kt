//package com.example.teclast_qc_application.calendar.read_phone_state
//
//import android.os.Build
//import android.util.Log
//
//fun getDeviceSerialNumber(): String {
//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        try {
//            Build.getSerial()
//        } catch (e: SecurityException) {
//            Log.e("TAG", "READ_PHONE_STATE permission is missing")
//            ""
//        }
//    } else {
//        Build.SERIAL
//    }
//}