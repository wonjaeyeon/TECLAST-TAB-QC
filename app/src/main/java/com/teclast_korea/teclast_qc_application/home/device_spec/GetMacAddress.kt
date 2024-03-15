package com.teclast_korea.teclast_qc_application.home.device_spec

import android.content.Context
import android.net.wifi.WifiManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.net.NetworkInterface
import java.util.*

@Composable
fun getDeviceMacAddress(): String {
    val context = LocalContext.current
    return getMacAddress(context)
}

private fun getMacAddress(context: Context): String {
    try {
        val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (intf in interfaces) {
            if (intf.name.equals("wlan0", ignoreCase = true) || intf.name.equals("eth0", ignoreCase = true)) {
                val macBytes = intf.hardwareAddress ?: return ""
                val res1 = StringBuilder()
                for (b in macBytes) {
                    res1.append(String.format("%02X:", b))
                }
                if (res1.isNotEmpty()) {
                    res1.deleteCharAt(res1.length - 1)
                }
                return res1.toString()
            }
        }
    } catch (ex: Exception) {
        // Handle exceptions if necessary
        return Exception().toString()
    }
    return "fail"
}


fun getMac(context: Context): String {
    val manager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = manager.connectionInfo
    if (info.macAddress == "" || info.macAddress.equals("02:00:00:00:00:00")) {
        return "unknown"
    }
    return info.macAddress.uppercase()
}