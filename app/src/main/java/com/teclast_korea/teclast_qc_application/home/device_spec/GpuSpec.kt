package com.teclast_korea.teclast_qc_application.home.device_spec

import java.io.BufferedReader
import java.io.InputStreamReader

fun getGpuInfo(): String {
    return try {
        val process = Runtime.getRuntime().exec("getprop ro.hardware.egl")
        BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
            reader.readLine()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return "unknown"
    }
}