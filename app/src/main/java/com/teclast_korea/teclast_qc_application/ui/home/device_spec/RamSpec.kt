package com.teclast_korea.teclast_qc_application.ui.home.device_spec

import android.app.ActivityManager
import android.content.Context
import androidx.compose.runtime.Composable

@Composable
fun MemoryInfo(context: Context): String {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val memoryInfo = ActivityManager.MemoryInfo()
    activityManager.getMemoryInfo(memoryInfo)

    val totalMemory = memoryInfo.totalMem / (1024 * 1024)
    val availableMemory = memoryInfo.availMem / (1024 * 1024)
    val usedMemory = totalMemory - availableMemory

    return "Usage: $usedMemory MB, Total: $totalMemory MB"
}