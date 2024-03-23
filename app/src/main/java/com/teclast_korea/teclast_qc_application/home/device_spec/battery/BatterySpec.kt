package com.teclast_korea.teclast_qc_application.home.device_spec.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.Composable
import java.lang.reflect.Method

@Composable
fun getBatteryCapacity(context: Context): String {
    val mPowerProfile: Any?
    var batteryCapacity = "Unknown"
    val powerProfileClass: Class<*>

    try {
        powerProfileClass = Class.forName("com.android.internal.os.PowerProfile")
        mPowerProfile = powerProfileClass
            .getConstructor(Context::class.java)
            .newInstance(context)

        val getAveragePower: Method = powerProfileClass.getMethod("getAveragePower", String::class.java)
        getAveragePower.isAccessible = true
        val batteryCapacityMah = getAveragePower.invoke(mPowerProfile, "battery.capacity") as Double
        batteryCapacity = "$batteryCapacityMah mAh"

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return batteryCapacity
}




fun getBatteryVoltage(context: Context): String {
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
        context.registerReceiver(null, filter)
    }

    val voltage = batteryStatus?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) ?: -1
    return if (voltage != -1) {
        "$voltage mV"
    } else {
        "unknown"
    }
}


fun getBatteryTemperature(context: Context): String {
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
