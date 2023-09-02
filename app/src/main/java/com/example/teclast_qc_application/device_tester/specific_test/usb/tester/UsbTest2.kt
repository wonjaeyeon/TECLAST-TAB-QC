package com.example.teclast_qc_application.device_tester.specific_test.usb.tester

import android.content.Context
import android.content.pm.PackageManager

// checkUsbHostModeAvailability test
fun usbTest2(context: Context): String {
    return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_USB_HOST)) {
        "Usb TEST : Success : USB Host Mode on : Card reader is available to use."


    } else {
        "Usb TEST : Success : USB Host Mode off : Card reader is not available to use."
    }
}


//fun checkCardReaderAvailability2(context: Context): String {
//    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
//    val deviceList: HashMap<String, UsbDevice> = usbManager.deviceList
//
//    return if (deviceList.isNotEmpty()) {
//        val deviceInfo = StringBuilder()
//        deviceInfo.append("Card reader(s) available to use:\n")
//
//        deviceList.values.forEach { device ->
//            deviceInfo.append("Device ID: ${device.deviceId}\n")
//            deviceInfo.append("Vendor ID: ${device.vendorId}\n")
//            deviceInfo.append("Product ID: ${device.productId}\n")
//            deviceInfo.append("Device Name: ${device.deviceName}\n")
//            deviceInfo.append("Device Class: ${device.deviceClass}\n")
//            deviceInfo.append("Device Subclass: ${device.deviceSubclass}\n")
//            deviceInfo.append("Device Protocol: ${device.deviceProtocol}\n")
//            deviceInfo.append("\n")
//        }
//
//        deviceInfo.toString()
//    } else {
//        "Card reader is not available to use."
//    }
//}