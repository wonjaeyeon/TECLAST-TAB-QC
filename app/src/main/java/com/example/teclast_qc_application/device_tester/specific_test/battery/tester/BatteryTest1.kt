package com.example.teclast_qc_application

import android.content.Context
import android.os.BatteryManager

fun batteryTestT1(context: Context): String {
    val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val batteryHealth = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    val threshold = 80

    if (batteryHealth >= threshold) {
        return "Battery TEST : Success : ($batteryHealth/100)"
    } else {
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




//fun checkStorageUsage(): Boolean {
//    val externalStorage = Environment.getExternalStorageDirectory()
//    val statFs = StatFs(externalStorage.path)
//    val blockSize = statFs.blockSizeLong
//    val totalBlocks = statFs.blockCountLong
//    val availableBlocks = statFs.availableBlocksLong
//
//    val usedBytes = blockSize * (totalBlocks - availableBlocks)
//    val totalBytes = blockSize * totalBlocks
//    val usedPercentage = (usedBytes.toDouble() / totalBytes.toDouble()) * 100
//    val threshold = 80.0
//
//    return usedPercentage <= threshold
//}

//fun checkQuality(context: Context): String {
//    val batteryHealthOk = checkBatteryHealth(context)
//    val storageUsageOk = checkStorageUsage()
//
//    return when {
//        !batteryHealthOk && !storageUsageOk -> "Battery and storage issues detected."
//        !batteryHealthOk -> "Battery issue detected."
//        !storageUsageOk -> "Storage issue detected."
//        else -> "No issues detected."
//    }
//}