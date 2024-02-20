package com.example.teclast_qc_application.device_tester.specific_test.usb.tester

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

fun usbTest1(context: Context): String {
    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    val deviceList = usbManager.deviceList

    if (deviceList.isEmpty()) {
        return "No USB devices connected."
    }

    val result = StringBuilder()
    //result.append("Number of connected USB devices: ${deviceList.size}\n")
    for (device in deviceList.values) {
        val deviceId = device.deviceId
        val manufacturerName = device.manufacturerName ?: "Unknown"
        val productName = device.productName ?: "Unknown"
        result.append("Usb TEST : Success\nDevice ID: $deviceId \nManufacturer: $manufacturerName \nProduct: $productName\n")
    }

    return result.toString()
}
