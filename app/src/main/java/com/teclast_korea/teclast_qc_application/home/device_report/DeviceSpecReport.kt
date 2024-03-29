package com.teclast_korea.teclast_qc_application.home.device_report

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import com.teclast_korea.teclast_qc_application.calendar.read_phone_state.getDeviceSerialNumber_v2
import com.teclast_korea.teclast_qc_application.home.device_spec.*
import com.teclast_korea.teclast_qc_application.home.device_spec.battery.getBatteryCapacity
import com.teclast_korea.teclast_qc_application.home.device_spec.battery.getBatteryTemperature
import com.teclast_korea.teclast_qc_application.home.device_spec.battery.getBatteryVoltage


@Composable
fun deviceSpecReportList(
    context: Context,
): List<Pair<String, String>> {
    return listOf(
        "Serial" to getDeviceSerialNumber_v2(),
        "MAC Address" to getMac(context),
        "IPv4" to getIPv4Addresses(),
        "IPv6" to getIPv6Addresses(),
        "Brand" to Build.BRAND,
        "MODEL" to Build.MODEL,
        "ID" to Build.ID,
        "Build Number" to Build.DISPLAY,
        "Manufacturer" to Build.MANUFACTURER,
        "INCREMENTAL" to Build.VERSION.INCREMENTAL,
        //"IMEI" to getIMEI(context),
        "SDK" to Build.VERSION.SDK_INT.toString(),
        "Android Version" to Build.VERSION.RELEASE,
        "CPU Spec" to getCpuInfo(),
        "GPU Spec" to getGpuInfo(),
        "Resolution" to context.resources.displayMetrics.run { "${widthPixels}x${heightPixels}" },
        "RAM" to MemoryInfo(context),
        "Internal Storage(without system)" to usedStorageInfo(context),
        //"Battery Spec" to getBatteryInfo(),
        "Battery Voltage" to getBatteryVoltage(context),
        "Battery Temperature" to getBatteryTemperature(context),
        "Battery Capacity" to getBatteryCapacity(context),
        //"Audio" to getAudioDeviceSpec(context),
        "NFC" to context.packageManager.hasSystemFeature(PackageManager.FEATURE_NFC).toString(),
        "Bluetooth version" to getBluetoothVersion(),
        "FINGERPRINT" to Build.FINGERPRINT,
    )
}
