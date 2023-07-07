package com.example.teclast_qc_application.device_tester.specific_test.battery.tester

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

fun getBatteryState(context: Context): String {
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
        context.registerReceiver(null, filter)
    }

    val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1

    return when (status) {
        BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
        BatteryManager.BATTERY_STATUS_FULL -> "Full"
        BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
        else -> "Error: Unable to determine battery status"
    }
}

