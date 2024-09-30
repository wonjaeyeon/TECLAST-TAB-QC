package com.teclast_korea.teclast_qc_application.ui.home.test_report

import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.CheckTestResultbyItem
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultState


fun TestReportList(
    state: TestResultState,
): List<Pair<String, String>> {

    return listOf(
        "CPU" to CheckTestResultbyItem(state, "CPU"),
        "GPU" to CheckTestResultbyItem(state, "GPU"),
        "RAM" to CheckTestResultbyItem(state, "RAM"),
        "Storage" to CheckTestResultbyItem(state, "ROM"),
        "Battery" to CheckTestResultbyItem(state, "Battery"),
        "Wifi" to CheckTestResultbyItem(state, "Wifi"),
        "Bluetooth" to CheckTestResultbyItem(state, "Bluetooth"),
        "USB" to CheckTestResultbyItem(state, "USB"),
        "Touch Panel" to CheckTestResultbyItem(state, "Touch Panel"),
        "LCD Display" to CheckTestResultbyItem(state, "LCD"),
        "Physical Buttons" to CheckTestResultbyItem(state, "Physical Button"),
        "GPS" to CheckTestResultbyItem(state, "GPS"),
        "G-Sensor" to CheckTestResultbyItem(state, "G-Sensor"),
        "Camera" to CheckTestResultbyItem(state, "Camera"),
        "Audio" to CheckTestResultbyItem(state, "Audio"),
        "Vibrator" to CheckTestResultbyItem(state, "Vibration"),
        "Flash Light" to CheckTestResultbyItem(state, "Flashlight"),
    )
}