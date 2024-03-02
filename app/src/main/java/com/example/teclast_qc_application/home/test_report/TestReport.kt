package com.example.teclast_qc_application.home.test_report

import android.content.Context
import com.example.teclast_qc_application.test_result.test_results_db.CheckTestResultDB
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState


fun TestReportList(
    state: TestResultState,
    context: Context,
    onEvent: (TestResultEvent) -> Unit,
): List<Pair<String, String>> {
    return listOf(
        "CPU" to CheckTestResultDB(state, onEvent, "CPU"),
        "GPU" to CheckTestResultDB(state, onEvent, "GPU"),
        "RAM" to CheckTestResultDB(state, onEvent, "RAM"),
        "Storage" to CheckTestResultDB(state, onEvent, "ROM"),
        "Battery" to CheckTestResultDB(state, onEvent, "Battery"),
        "Wifi" to CheckTestResultDB(state, onEvent, "Wifi"),
        "Bluetooth" to CheckTestResultDB(state, onEvent, "Bluetooth"),
        "USB" to CheckTestResultDB(state, onEvent, "USB"),
        "Touch Panel" to CheckTestResultDB(state, onEvent, "Touch Panel"),
        "LCD Display" to CheckTestResultDB(state, onEvent, "LCD"),
        "Physical Buttons" to CheckTestResultDB(state, onEvent, "Physical Button"),
        "GPS" to CheckTestResultDB(state, onEvent, "GPS"),
        "G-Sensor" to CheckTestResultDB(state, onEvent, "G-Sensor"),
        "Camera" to CheckTestResultDB(state, onEvent, "Camera"),
        "Audio" to CheckTestResultDB(state, onEvent, "Audio"),
        "Vibrator" to CheckTestResultDB(state, onEvent, "Vibration"),
        "Flash Light" to CheckTestResultDB(state, onEvent, "Flashlight"),
    )
}