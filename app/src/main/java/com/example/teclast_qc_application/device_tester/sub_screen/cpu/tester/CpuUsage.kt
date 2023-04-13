package com.example.teclast_qc_application.device_tester.sub_screen.cpu.tester

//import java.io.BufferedReader
//import java.io.FileReader
//import java.io.IOException
//
//fun getCurrentCpuUsage(): Float {
//    try {
//        val reader = BufferedReader(FileReader("/proc/stat"))
//        val load = reader.readLine().split(" ")
//        reader.close()
//
//
//        val user = load[2].toLong()
//        val nice = load[3].toLong()
//        val system = load[4].toLong()
//        val idle = load[5].toLong()
//        val iowait = load[6].toLong()
//        val irq = load[7].toLong()
//        val softirq = load[8].toLong()
//
//        val totalCpuTime1 = user + nice + system + idle + iowait + irq + softirq
//        val totalIdleTime1 = idle + iowait
//
//        Thread.sleep(100)
//
//        val reader2 = BufferedReader(FileReader("/proc/stat"))
//        val load2 = reader2.readLine().split(" ")
//        reader2.close()
//
//        val user2 = load2[2].toLong()
//        val nice2 = load2[3].toLong()
//        val system2 = load2[4].toLong()
//        val idle2 = load2[5].toLong()
//        val iowait2 = load2[6].toLong()
//        val irq2 = load2[7].toLong()
//        val softirq2 = load2[8].toLong()
//
//        val totalCpuTime2 = user2 + nice2 + system2 + idle2 + iowait2 + irq2 + softirq2
//        val totalIdleTime2 = idle2 + iowait2
//
//        val totalCpuTimeDiff = totalCpuTime2 - totalCpuTime1
//        val totalIdleTimeDiff = totalIdleTime2 - totalIdleTime1
//
//        return ((totalCpuTimeDiff - totalIdleTimeDiff) / totalCpuTimeDiff.toFloat()) * 100
//    } catch (e: IOException) {
//        e.printStackTrace()
//        return 0f
//    }
//}
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun getAppUsageStats(context: Context): List<UsageStats> {
    val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    val currentTime = System.currentTimeMillis()
    val oneDayAgo = currentTime - (1000 * 60 * 60 * 24)

    return usageStatsManager.queryUsageStats(
        UsageStatsManager.INTERVAL_DAILY,
        oneDayAgo,
        currentTime
    )
}
