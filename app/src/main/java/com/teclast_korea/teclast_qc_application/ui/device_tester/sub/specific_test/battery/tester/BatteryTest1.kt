package com.teclast_korea.teclast_qc_application

import android.content.Context
import android.os.BatteryManager
import com.teclast_korea.teclast_qc_application.domain.qc_results.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*

fun batteryTestT1(state: TestResultState,
                  onEvent: (TestResultEvent) -> Unit, context: Context): String {
    val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val batteryHealth = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    val threshold = 80

    if (batteryHealth >= threshold) {
        AddTestResult(state = state, onEvent = onEvent, "Battery TEST 1", "Success", Date().toString())
        return "Battery TEST : Success : ($batteryHealth/100)"
    } else {
        AddTestResult(state = state, onEvent = onEvent, "Battery TEST 1", "Fail", Date().toString())
        return "Battery TEST : Fail : ($batteryHealth/100)"
    }
}

fun getDeviceTemperature(context: Context): String {
    val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val temperature = batteryManager.getIntProperty(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE)
    if (temperature == 5) {
        return "battery is over voltage"
    } else if (temperature == 4) {
        return "battery is over heat"
    } else if (temperature == 3) {
        return "battery is cold"
    } else if (temperature == 2) {
        return "battery is dead"
    } else if (temperature == 1) {
        return "battery is good"
    } else if (temperature == 1) {
        return "battery is unknown"
    } else {
        return "battery is not present"
    }
}
