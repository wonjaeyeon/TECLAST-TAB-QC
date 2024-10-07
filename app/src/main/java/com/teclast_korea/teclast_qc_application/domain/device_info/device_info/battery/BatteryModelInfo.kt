package com.teclast_korea.teclast_qc_application.domain.device_info.device_info.battery


import androidx.compose.runtime.Composable
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
@Composable
fun getBatteryInfo(): String {
    return  try {
        val reader = BufferedReader(FileReader("/proc/interrupts"))
        val cpuInfo = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            cpuInfo.append(line + "\n")
        }
        reader.close()
        cpuInfo.toString()
    } catch (e: IOException) {
        "Unable to read Battery info"
    }
}