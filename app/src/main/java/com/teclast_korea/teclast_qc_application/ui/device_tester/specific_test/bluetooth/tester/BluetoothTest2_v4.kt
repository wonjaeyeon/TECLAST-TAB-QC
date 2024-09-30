package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.bluetooth.tester

import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


fun BluetoothTestT2_v4(context: Context): String {

    val bluetoothAdapter = context.getSystemService(BluetoothManager::class.java)?.adapter
    val devices = bluetoothAdapter?.bondedDevices?.toList() ?: listOf()


//    LaunchedEffect(Unit) {
//        devices.value = bluetoothAdapter?.bondedDevices?.toList() ?: listOf()
//    }

    return devices.joinToString(separator = "\n") { device ->
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request Bluetooth permissions
        }
        "Name: ${device.name}, Address: ${device.address}"
    }

}
