package com.teclast_korea.teclast_qc_application.domain.device_info.device_info

import android.os.Build
import androidx.compose.runtime.Composable

@Composable
fun getBluetoothVersion(): String {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> "Bluetooth 5.0"
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> "Bluetooth 4.2"
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> "Bluetooth 4.1"
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 -> "Bluetooth 4.0"
        else -> "Bluetooth version not available"
    }
}