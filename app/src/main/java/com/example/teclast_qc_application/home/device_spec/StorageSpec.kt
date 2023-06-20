package com.example.teclast_qc_application.home.device_spec

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import androidx.compose.runtime.Composable

@Composable
fun totalStorageInfo(path: String): String {
    val statFs = StatFs(path)
    val total: Long
    total = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
        statFs.totalBytes
    } else {
        statFs.blockCountLong * statFs.blockSizeLong
    }
    val totalInGb = total / (1024 * 1024 * 1024)
    return "Total storage: $totalInGb GB"
}

//@Composable
//fun usedStorageInfo(): String {
//    val statFs = StatFs(Environment.getDataDirectory().getPath())
//
//    val used: Long
//    used = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
//        statFs.totalBytes - statFs.availableBytes
//    } else {
//        statFs.blockCountLong * statFs.blockSizeLong - statFs.availableBlocksLong * statFs.blockSizeLong
//    }
//    val usedInGb = used / (1024 * 1024 * 1024)
//    return "Used storage: $usedInGb GB"
//}

//@Composable
//fun usedStorageInfo() : String {
//    val totalSpace = File("/").getTotalSpace()
//    val totalInternalSpace = totalSpace - Environment.getExternalStorageDirectory().getTotalSpace()
//    return "Used storage: ${totalInternalSpace / (1024 * 1024 * 1024)} GB"
//}

@Composable
fun usedStorageInfo(context: Context): String {
    val statFs = StatFs(context.filesDir.absolutePath)

    val totalBytes: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        statFs.totalBytes
    } else {
        statFs.blockCountLong * statFs.blockSizeLong
    }

    val availableBytes: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        statFs.availableBytes
    } else {
        statFs.availableBlocksLong * statFs.blockSizeLong
    }

    val usedBytes = totalBytes - availableBytes
    val totalGb = totalBytes / (1024.0 * 1024 * 1024)
    val usedGb = usedBytes / (1024.0 * 1024 * 1024)

    return "Usage: %.2f GB, Total: %.2f GB".format(usedGb, totalGb)
}

@Composable
fun usedStorageInfoV2(): String {
    val totalSpace = Environment.getDataDirectory().totalSpace
    val freeSpace = Environment.getDataDirectory().freeSpace
    val usedSpace = totalSpace - freeSpace
    val totalSpaceInGb = totalSpace / (1024.0 * 1024 * 1024)
    val usedSpaceInGb = usedSpace / (1024.0 * 1024 * 1024)

    return "Usage: %.2f GB, Total: %.2f GB".format(usedSpaceInGb, totalSpaceInGb)
}