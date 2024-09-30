package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.battery.tester

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager


fun BatteryVoltageTest(context: Context): String {
    // LocalContext is a Composable that provides the context

    val batteryVoltageValue = batteryVoltage(context)
    return "Battery Voltage: $batteryVoltageValue"
}

fun batteryVoltage(context: Context): String {
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
        context.registerReceiver(null, filter)
    }

    val voltage = batteryStatus?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) ?: -1
    return if (voltage != -1) {
        "$voltage"
    } else {
        "Error: Unable to determine battery voltage"
    }
}