package com.teclast_korea.teclast_qc_application.utility

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

fun setPowerOffAlarm10SecAfter(context: Context) {
    // Get the current time
    val calendar = Calendar.getInstance().apply {
        add(Calendar.MINUTE, 1) // Add 10 seconds to the current time
    }

    // Extract hour, minute, and second
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    Log.d("SetPowerOffAlarm", "Hour: $hour")
    val minute = calendar.get(Calendar.MINUTE)
    Log.d("SetPowerOffAlarm", "Minute: $minute")
    val second = calendar.get(Calendar.SECOND)
    Log.d("SetPowerOffAlarm", "Second: $second")

    val intent = Intent().apply {
        action = "android.intent.action.setting.time.status"
        component = ComponentName("com.android.settings", "com.unisoc.settings.timerpower.AlarmInitReceiver")
        putExtra("hour", hour.toString())
        putExtra("enabled", "true")
        putExtra("label", "off")
        putExtra("minutes", minute.toString())
        putExtra("seconds", second.toString()) // If your receiver supports seconds
        putExtra("id", "2")
    }
    try {
        context.sendBroadcast(intent)
        Log.d("SetPowerOffAlarm", "Alarm set for $hour:$minute:$second (1 minute from now)")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
