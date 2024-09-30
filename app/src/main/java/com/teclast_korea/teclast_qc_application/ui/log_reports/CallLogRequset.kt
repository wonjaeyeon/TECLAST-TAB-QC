package com.teclast_korea.teclast_qc_application.ui.log_reports

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import java.io.BufferedReader
import java.io.InputStreamReader

private fun fetchAndHighlightHardwareLogs(): AnnotatedString {
    return try {
        // Filter logcat output for hardware-related tags
        val process = Runtime.getRuntime().exec("logcat -d")
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

        buildAnnotatedString {
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                when {
                    line!!.contains("PowerManager") -> {
                        withStyle(SpanStyle(color = Color.Red)) {
                            append(line!!)
                        }
                    }
                    line!!.contains("BatteryService") -> {
                        withStyle(SpanStyle(color = Color.Green)) {
                            append(line!!)
                        }
                    }
                    line!!.contains("Wifi") -> {
                        withStyle(SpanStyle(color = Color.Blue)) {
                            append(line!!)
                        }
                    }
                    line!!.contains("Bluetooth") -> {
                        withStyle(SpanStyle(color = Color.Magenta)) {
                            append(line!!)
                        }
                    }
                }
                append("\n")
            }
        }
    } catch (e: Exception) {
        buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Red)) {
                append("Error displaying logs: ${e.message}")
            }
        }
    }
}

fun fetchLogs(): String {
    val logcatProcess = Runtime.getRuntime().exec(arrayOf("logcat", "-d"))
    val bufferedReader = BufferedReader(InputStreamReader(logcatProcess.inputStream))
    return bufferedReader.readText()
}



fun requestBugReport(context: Context) {
    val bugReportIntent = Intent(Intent.ACTION_BUG_REPORT)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        bugReportIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    if (bugReportIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(bugReportIntent)
    } else {
        // No handler for bug report intent
        print("nothing to show")
    }
}
