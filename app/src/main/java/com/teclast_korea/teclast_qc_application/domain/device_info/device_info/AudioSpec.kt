package com.teclast_korea.teclast_qc_application.domain.device_info.device_info


import android.media.AudioDeviceInfo


private fun deviceTypeToString(type: Int): String {
    return when (type) {
        AudioDeviceInfo.TYPE_BLUETOOTH_A2DP -> "Bluetooth A2DP"
        AudioDeviceInfo.TYPE_BUILTIN_EARPIECE -> "Built-in Earpiece"
        AudioDeviceInfo.TYPE_BUILTIN_SPEAKER -> "Built-in Speaker"
        else -> "Unknown Type"
    }
}


