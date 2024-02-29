package com.example.teclast_qc_application.device_tester.specific_test.wifi.tester

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*

fun wifiConnectionTest(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context
): String {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val isConnectedToWifi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return "Not connected to Wi-Fi"
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return "Not connected to Wi-Fi"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    if (isConnectedToWifi) {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(state = state, onEvent = onEvent, "Wifi TEST 1", "Success", Date().toString())
        onEvent(TestResultEvent.SaveTestResult)
        return "Connected to Wi-Fi"
    } else {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(state = state, onEvent = onEvent, "Wifi TEST 1", "Fail", Date().toString())
        onEvent(TestResultEvent.SaveTestResult)
        return "Not connected to Wi-Fi"
    }
}