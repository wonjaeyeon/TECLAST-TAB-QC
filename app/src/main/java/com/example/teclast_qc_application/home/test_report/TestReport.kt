package com.example.teclast_qc_application.home.test_report

import android.content.Context
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent


fun TestReportList(
    context: Context,
    onEvent: (TestResultEvent) -> Unit,
): List<Pair<String, String>> {
    return listOf(
        "CPU" to onEvent(TestResultEvent.FindbyItemName("CPU")).toString(),
        "GPU" to "PASS",
        "RAM" to "PASS",
        "Storage" to "PASS",
        "Battery" to "PASS",
        "USB" to "PASS",
        "Bluetooth" to "FAIL",
        "Wifi" to "PASS",
        "Camera" to "PASS",
        "Screen" to "FAIL",
        "Speaker" to "PASS",
        "Microphone" to "PASS",
        "Vibrator" to "PASS",
        "GPS" to "PASS",
        "Auto-Sleep" to "FAIL",
        "Brightness" to "PASS",
    )
}