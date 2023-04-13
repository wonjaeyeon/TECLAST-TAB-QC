package com.example.teclast_qc_application.device_tester.sub_screen.usb.tester

import android.content.Context
import android.hardware.usb.UsbManager


fun getUsbConnectionState(context: Context): String {
    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    val usbDevices = usbManager.deviceList

    return if (usbDevices.isNotEmpty()) {
        "USB device connected"
    } else {
        "No USB device connected"
    }
}




fun checkUsbConnection(context: Context): String {
    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    val deviceList = usbManager.deviceList

    if (deviceList.isEmpty()) {
        return "No USB devices connected."
    }

    val result = StringBuilder("Connected USB devices:\n")
    for (device in deviceList.values) {
        val deviceId = device.deviceId
        val manufacturerName = device.manufacturerName
        val productName = device.productName
        result.append("Device ID: $deviceId, Manufacturer: $manufacturerName, Product: $productName\n")
    }

    return result.toString()
}