package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.battery.tester

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.PowerManager
import androidx.annotation.RequiresApi


fun getBatteryTemperatureInTest(context: Context): String {
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
        context.registerReceiver(null, filter)
    }

    // Battery temperature is returned in tenths of a degree Celsius.
    val temperatureTenths = batteryStatus?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) ?: -1

    return if (temperatureTenths != -1) {
        val temperatureCelsius = temperatureTenths / 10.0
        "$temperatureCelsius °C"
    } else {
        "unknown"
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
fun checkDeviceThermalStatus(context: Context): String {
    return try {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val thermalStatus = powerManager.currentThermalStatus

        if (thermalStatus == PowerManager.THERMAL_STATUS_NONE || thermalStatus == PowerManager.THERMAL_STATUS_LIGHT) {
            "Device Thermal State: ${getThermalStatusDescription(thermalStatus)}"
        } else {
            "Device Thermal State: ${getThermalStatusDescription(thermalStatus)}"
        }
    } catch (error: Exception) {
        "Error: ${error.message}"
    }
}

fun getThermalStatusDescription(thermalStatus: Int): String {
    return when (thermalStatus) {
        PowerManager.THERMAL_STATUS_NONE -> "THERMAL_STATUS_NONE(NO PROBLEM)"
        PowerManager.THERMAL_STATUS_LIGHT -> "THERMAL_STATUS_LIGHT(Few Restrictions. NO PROBLEM)"
        PowerManager.THERMAL_STATUS_MODERATE -> "THERMAL_STATUS_MODERATE(Few Restrictions. NO PROBLEM)"
        PowerManager.THERMAL_STATUS_SEVERE -> "THERMAL_STATUS_SEVERE(SEVERE PROBLEM)"
        PowerManager.THERMAL_STATUS_CRITICAL -> "THERMAL_STATUS_CRITICAL(CRITICAL PROBLEM)"
        PowerManager.THERMAL_STATUS_EMERGENCY -> "THERMAL_STATUS_EMERGENCY(EMERGENCY PROBLEM)"
        PowerManager.THERMAL_STATUS_SHUTDOWN -> "THERMAL_STATUS_SHUTDOWN(SHUTDOWN PROBLEM)"
        else -> "Unknown"
    }
}