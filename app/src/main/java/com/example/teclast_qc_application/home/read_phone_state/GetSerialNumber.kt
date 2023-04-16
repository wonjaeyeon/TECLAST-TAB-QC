package com.example.teclast_qc_application.calendar.read_phone_state
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


import android.content.Context
import android.os.Build
import android.provider.Settings

fun getDeviceSerialNumber(context: Context): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            try {
                return Build.getSerial()

            } catch (e: SecurityException) {
                "SecurityException: READ_PHONE_STATE permission is missing"
            }
        } else {
            "READ_PHONE_STATE permission is missing"
        }
    }




fun getAndroidId(context: Context): String {
    return Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}