package com.example.teclast_qc_application.device_tester.specific_test.wifi.tester

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun getWifiConnectionStatus(context: Context): String {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val isConnectedToWifi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return "Not connected to Wi-Fi"
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return "Not connected to Wi-Fi"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    return if (isConnectedToWifi) "Connected to Wi-Fi" else "Not connected to Wi-Fi"
}