package com.teclast_korea.teclast_qc_application.device_tester.specific_test.cpu.tester

import android.content.Context
import android.content.Context.HARDWARE_PROPERTIES_SERVICE
import android.os.HardwarePropertiesManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


fun getTemperatureInCelsius(): String? {
    try {
        val process = Runtime.getRuntime().exec("/sys/class/thermal/thermal_zone0/temp")
        process.waitFor();
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val temperatureString = reader.readLine()
        reader.close()
        process.destroy()
        if(temperatureString == null) return "null value came"
        else{
            val output = temperatureString.toFloat() / 1000
            return output.toString()
        }
        // The temperature is usually reported in millidegrees Celsius, so divide by 1000 to get degrees Celsius
//        val output = temperatureString.toFloat() / 1000
//        return output
    } catch (e: IOException) {
        e.printStackTrace()
        return "IOException"
    }
}

fun getTemperatureInCelsius2(context: Context): String? {
    try {
        val hwpm = context.getSystemService(HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager
        val temperatures = hwpm.getDeviceTemperatures(HardwarePropertiesManager.DEVICE_TEMPERATURE_CPU, HardwarePropertiesManager.TEMPERATURE_CURRENT)
        if (temperatures.isEmpty()) {
            return "CPU temperature information not available"
        }
        val output = temperatures.toString()
        return output
    } catch (e: IOException) {
        e.printStackTrace()
        return "IOException"

    }
}
        // The temperature is usually reported in millidegrees Celsius, so divide by 1000 to get degrees Celsius
//import android.content.Context
//import android.os.Build
//import android.os.HardwarePropertiesManager
//import androidx.annotation.RequiresApi
//
//@RequiresApi(Build.VERSION_CODES.N)
//fun checkCpuTemperature(context: Context): String {
//    val hardwarePropertiesManager = context.getSystemService(Context.HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager
//
//    val cpuTemperatures = hardwarePropertiesManager.getDeviceTemperatures( HardwarePropertiesManager.DEVICE_TEMPERATURE_CPU, HardwarePropertiesManager.TEMPERATURE_THROTTLING)
//    if (cpuTemperatures.isEmpty()) {
//        return "CPU temperature information not available"
//    }
//
//
//    return if (cpuTemperatures.max() < THRESHOLD_TEMPERATURE) {
//        "CPU temperature is alright: THROTTLING_TEMPERATURE $cpuTemperatures°C"
//    } else {
//        "CPU temperature is too high:THROTTLING_TEMPERATURE $cpuTemperatures°C"
//    }
//}
//
//// Set a threshold temperature in Celsius that you consider as safe
//private const val THRESHOLD_TEMPERATURE = 70f


fun thermal() : String{
    var temp: String
    var type: String?
    var output = "aaa"
    for (i in 0..5) {
        temp = thermalTemp(i)
        if (temp != "0.0") {
            type = thermalType(i)
            if (type != null) {
                println("ThermalValues $type : $temp\n")
                output += "ThermalValues $type : $temp\n"
            }
        }
    }
    return output
}

fun thermalTemp(i: Int): String {
    var tempString: String? = null
    var temp = 0f
    try {
        val process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone$i/temp")
        process.waitFor()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val line = reader.readLine()
        if (line != null) {
            temp = line.toFloat()
        }
        reader.close()
        process.destroy()
        if (temp.toInt() != 0) {
            temp = when {
                temp > 10000 -> temp / 1000
                temp > 1000 -> temp / 100
                temp > 100 -> temp / 10
                else -> temp
            }
        } else {
            tempString = "안 됩니다 ㅠㅠ"
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return tempString ?: temp.toString()
}

fun thermalType(i: Int): String? {
    var type: String? = null
    try {
        val process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone$i/type")
        process.waitFor()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val line = reader.readLine()
        if (line != null) {
            type = line
        }
        reader.close()
        process.destroy()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return type
}
