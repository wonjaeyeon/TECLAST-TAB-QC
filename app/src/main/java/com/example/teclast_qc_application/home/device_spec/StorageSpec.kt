package com.example.teclast_qc_application.home.device_spec

import android.content.Context
import android.os.Build
import android.os.StatFs
import androidx.compose.runtime.Composable


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
