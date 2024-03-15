package com.teclast_korea.teclast_qc_application.device_tester.specific_test.wifi.tester

//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkCapabilities
//import android.net.TrafficStats
//import android.os.Build
//
//data class DataUsage(val receivedBytes: Long, val sentBytes: Long)
//
//fun getWifiDataUsage(context: Context): DataUsage? {
//    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    var isWifiConnected = false
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { networkCapabilities ->
//            isWifiConnected = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
//        }
//    } else {
//        @Suppress("DEPRECATION")
//        val activeNetworkInfo = connectivityManager.activeNetworkInfo
//        isWifiConnected = activeNetworkInfo != null && activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
//    }
//
//    if (isWifiConnected) {
//        val receivedBytes = TrafficStats.getUidRxBytes(context.applicationInfo.uid)
//        val sentBytes = TrafficStats.getUidTxBytes(context.applicationInfo.uid)
//
//        if (receivedBytes != TrafficStats.UNSUPPORTED.toLong() && sentBytes != TrafficStats.UNSUPPORTED.toLong()) {
//            return DataUsage(receivedBytes, sentBytes)
//        }
//    }
//    return null
//}


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.os.Build
import java.text.DecimalFormat

fun getWifiDataUsage(context: Context): String? {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var isWifiConnected = false

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { networkCapabilities ->
            isWifiConnected = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    } else {
        @Suppress("DEPRECATION")
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        isWifiConnected = activeNetworkInfo != null && activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    if (isWifiConnected) {
        val receivedBytes = TrafficStats.getUidRxBytes(context.applicationInfo.uid)
        val sentBytes = TrafficStats.getUidTxBytes(context.applicationInfo.uid)

        if (receivedBytes != TrafficStats.UNSUPPORTED.toLong() && sentBytes != TrafficStats.UNSUPPORTED.toLong()) {
            val formatter = DecimalFormat("#,###")
            val receivedBytesStr = formatter.format(receivedBytes)
            val sentBytesStr = formatter.format(sentBytes)
            return "Received Bytes: $receivedBytesStr\nSent Bytes: $sentBytesStr"
        }
    }
    return null
}
