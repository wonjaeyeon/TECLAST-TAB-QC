package com.teclast_korea.teclast_qc_application.home.device_spec

import androidx.compose.runtime.Composable
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException


@Composable
fun getCpuInfo(): String {
    return try {
        val reader = BufferedReader(FileReader("/proc/cpuinfo"))
        val cpuInfo = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            cpuInfo.append(line + "\n")
        }
        reader.close()
        cpuInfo.split("\n\n").forEach {
            if (it.contains("Hardware")) {
                return@getCpuInfo it
            }
        }
        cpuInfo.toString()
    } catch (e: IOException) {
        "Unable to read CPU info"
    }
}