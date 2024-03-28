package com.teclast_korea.teclast_qc_application.home.device_spec

import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.opengl.GLES20
import java.io.BufferedReader
import java.io.InputStreamReader

fun getGpuInfo(): String {
    val gpuInfo = StringBuilder()



    // Run these in an OpenGL ES context
    val renderer = GLES20.glGetString(GLES20.GL_RENDERER) ?: "Unknown"
    val vendor = GLES20.glGetString(GLES20.GL_VENDOR) ?: "Unknown"
    val version = GLES20.glGetString(GLES20.GL_VERSION) ?: "Unknown"
    val extensions = GLES20.glGetString(GLES20.GL_EXTENSIONS) ?: "Unknown"

    gpuInfo.append("Renderer: ").append(renderer).append("\n")
    gpuInfo.append("Vendor: ").append(vendor).append("\n")
    gpuInfo.append("Version: ").append(version).append("\n")
    gpuInfo.append("Extensions: ").append(extensions)

    return gpuInfo.toString()
}

fun getGlEsVersion(context: Context): String {
    val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
    val configurationInfo = activityManager.deviceConfigurationInfo
    val gpuInfo = buildString {
        append(configurationInfo.toString())
        append("\n")
        append(configurationInfo.glEsVersion)
    }
    return gpuInfo
}


fun getGlesInformation(): String {
    val process = ProcessBuilder()
        .command("adb", "shell", "dumpsys", "|", "grep", "GLES")
        .redirectErrorStream(true)
        .start()

    val inputStream = process.inputStream
    val outputStream = process.outputStream
    val errorStream = process.errorStream

    val inputStreamReader = InputStreamReader(inputStream)
    val bufferedReader = BufferedReader(inputStreamReader)
    val stringBuilder = StringBuilder()

    var line: String?
    while (bufferedReader.readLine().also { line = it } != null) {
        stringBuilder.append(line).append("\n")
    }

    inputStream.close()
    outputStream.close()
    errorStream.close()

    return stringBuilder.toString().trim()
}

