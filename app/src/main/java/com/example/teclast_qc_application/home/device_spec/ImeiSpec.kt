package com.example.teclast_qc_application.home.device_spec

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat


//@Composable
//fun getIMEI(context: Context): String {
//    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//
//    return if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//        telephonyManager.imei ?: "IMEI not available"
//    } else {
//        "Permission not granted"
//    }
//}