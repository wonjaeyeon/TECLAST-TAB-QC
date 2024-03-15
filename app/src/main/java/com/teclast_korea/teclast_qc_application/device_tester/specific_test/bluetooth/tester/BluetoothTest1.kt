package com.teclast_korea.teclast_qc_application.device_tester.specific_test.bluetooth.tester

import android.bluetooth.BluetoothAdapter

fun BluetoothTestT1(): String {
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    if(bluetoothAdapter != null && bluetoothAdapter.isEnabled) {
        return "Bluetooth Test : Success : Bluetooth is enabled"
    } else {
        return "Bluetooth Test: Failed : Bluetooth is disabled"
    }
}