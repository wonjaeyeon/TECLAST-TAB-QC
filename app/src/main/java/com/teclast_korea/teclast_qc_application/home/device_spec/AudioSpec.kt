package com.teclast_korea.teclast_qc_application.home.device_spec

import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.os.Build


fun getAudioDeviceSpec(context: Context): String {
    if (Build.VERSION.SDK_INT < 23) {
        return "Audio device information not available on Android versions below Marshmallow."
    }

    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val devices = audioManager.getDevices(AudioManager.GET_DEVICES_INPUTS)

    if (devices.size == 0) {
        return "No audio devices found."
    }

    val deviceSpecs = StringBuilder("Audio Devices:\n")
    for (device in devices) {
        val info = StringBuilder()
        info.append("  ").append(deviceTypeToString(device.type))
        info.append(if (device.isSource) "(in): " else "(out): ")

        if (device.channelCounts.size > 0) {
            info.append("channels=").append(device.channelCounts.contentToString()).append(", ")
        }
        if (device.encodings.size > 0) {
            info.append("encodings=").append(device.encodings.contentToString()).append(", ")
        }
        if (device.sampleRates.size > 0) {
            info.append("sample rates=").append(device.sampleRates.contentToString()).append(", ")
        }
        info.append("id=").append(device.id)
        deviceSpecs.append(info.toString()).append("\n")
    }

    return deviceSpecs.toString().trim { it <= ' ' } // Remove the last newline
}

private fun deviceTypeToString(type: Int): String {
    return when (type) {
        AudioDeviceInfo.TYPE_BLUETOOTH_A2DP -> "Bluetooth A2DP"
        AudioDeviceInfo.TYPE_BUILTIN_EARPIECE -> "Built-in Earpiece"
        AudioDeviceInfo.TYPE_BUILTIN_SPEAKER -> "Built-in Speaker"
        else -> "Unknown Type"
    }
}


