package com.teclast_korea.teclast_qc_application.device_tester.specific_test.wifi.tester

import android.content.Context
import android.net.wifi.WifiManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*

@Suppress("DEPRECATION")
fun getWifiSignalStrength(context: Context): Int {
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = wifiManager.connectionInfo
    return WifiManager.calculateSignalLevel(info.rssi, 100)
}

fun wifiSignalStrengthTest(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    requiredSignalStrength: MutableState<Int> = mutableIntStateOf(20)
): String {
    val wifiSignalStrength = getWifiSignalStrength(context)
    var result = ""
    if (wifiSignalStrength < requiredSignalStrength.value) {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(state = state, onEvent = onEvent, "Wifi TEST 2", "Fail", Date().toString())
        onEvent(TestResultEvent.SaveTestResult)
        result += "WIFI : Fail: Signal strength is weak\nSignal Strength: ${wifiSignalStrength}/100"
    } else {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(state = state, onEvent = onEvent, "Wifi TEST 2", "Success", Date().toString())
        onEvent(TestResultEvent.SaveTestResult)
        result += "WIFI : Pass: Signal strength is strong\nSignal Strength: ${wifiSignalStrength}/100"
    }
    return result
}