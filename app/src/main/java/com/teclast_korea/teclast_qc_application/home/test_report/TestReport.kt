package com.teclast_korea.teclast_qc_application.home.test_report

import android.content.Context
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.CheckTestResultbyItem
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState


fun TestReportList(
    state: TestResultState,
    context: Context,
    onEvent: (TestResultEvent) -> Unit,
): List<Pair<String, String>> {

    return listOf(
        "CPU" to CheckTestResultbyItem(state, onEvent, "CPU"),
        "GPU" to CheckTestResultbyItem(state, onEvent, "GPU"),
        "RAM" to CheckTestResultbyItem(state, onEvent, "RAM"),
        "Storage" to CheckTestResultbyItem(state, onEvent, "ROM"),
        "Battery" to CheckTestResultbyItem(state, onEvent, "Battery"),
        "Wifi" to CheckTestResultbyItem(state, onEvent, "Wifi"),
        "Bluetooth" to CheckTestResultbyItem(state, onEvent, "Bluetooth"),
        "USB" to CheckTestResultbyItem(state, onEvent, "USB"),
        "Touch Panel" to CheckTestResultbyItem(state, onEvent, "Touch Panel"),
        "LCD Display" to CheckTestResultbyItem(state, onEvent, "LCD"),
        "Physical Buttons" to CheckTestResultbyItem(state, onEvent, "Physical Button"),
        "GPS" to CheckTestResultbyItem(state, onEvent, "GPS"),
        "G-Sensor" to CheckTestResultbyItem(state, onEvent, "G-Sensor"),
        "Camera" to CheckTestResultbyItem(state, onEvent, "Camera"),
        "Audio" to CheckTestResultbyItem(state, onEvent, "Audio"),
        "Vibrator" to CheckTestResultbyItem(state, onEvent, "Vibration"),
        "Flash Light" to CheckTestResultbyItem(state, onEvent, "Flashlight"),
    )
}