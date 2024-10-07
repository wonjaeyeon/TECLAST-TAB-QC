package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.flash_light.tester


import android.content.Context
import android.hardware.camera2.CameraManager

fun toggleFlashLight(context: Context, isFlashOn: Boolean): String {
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    var testResult: String
    try {
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraId, isFlashOn)
        testResult = if (isFlashOn) "Flashlight is on" else "Flashlight is off"
    } catch (e: Exception) {
        e.printStackTrace()
        testResult = "Cannot find Flashlight"
    }
    return testResult

}
