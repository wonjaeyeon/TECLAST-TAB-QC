package com.example.teclast_qc_application.home.device_spec

import android.content.Context
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