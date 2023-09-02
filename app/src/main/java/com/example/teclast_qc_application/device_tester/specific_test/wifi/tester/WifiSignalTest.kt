package com.example.teclast_qc_application.device_tester.specific_test.wifi.tester

import android.content.Context
import android.net.wifi.WifiManager

@Suppress("DEPRECATION")
fun getWifiSignalStrength(context: Context): Int {
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = wifiManager.connectionInfo
    return WifiManager.calculateSignalLevel(info.rssi, 100)
}