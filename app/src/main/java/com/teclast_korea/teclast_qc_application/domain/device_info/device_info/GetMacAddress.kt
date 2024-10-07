package com.teclast_korea.teclast_qc_application.domain.device_info.device_info

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager


@SuppressLint("HardwareIds")
fun getMac(context: Context): String {
    val manager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = manager.connectionInfo
    if (info.macAddress == "" || info.macAddress.equals("02:00:00:00:00:00")) {
        return "unknown"
    }
    return info.macAddress.uppercase()
}