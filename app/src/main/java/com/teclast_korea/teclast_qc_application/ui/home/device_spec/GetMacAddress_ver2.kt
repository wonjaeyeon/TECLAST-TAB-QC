package com.teclast_korea.teclast_qc_application.ui.home.device_spec

import java.net.NetworkInterface
import java.util.*


fun getMacAddress2(): String {
    try {
        val all: List<NetworkInterface> = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (nif in all) {
            if (!nif.name.equals("wlan0", ignoreCase = true)) continue
            val macBytes = nif.hardwareAddress ?: return ""
            val res1 = StringBuilder()
            for (b in macBytes) {
                res1.append(String.format("%02X", b))
            }
            if (res1.length > 0) {
                res1.deleteCharAt(res1.length - 1)
            }
            return res1.toString()
        }
    } catch (ex: Exception) {
        //handle exception
    }
    return ""
}


