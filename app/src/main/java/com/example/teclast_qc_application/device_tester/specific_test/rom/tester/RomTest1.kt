package com.example.teclast_qc_application.device_tester.specific_test.rom.tester

import android.os.Environment
import android.os.StatFs

fun romTest1(): String {
    try {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        val availableSpaceInGB = availableBlocks * blockSize / (1024 * 1024 * 1024)

        // You may adjust the threshold as per your requirement
        return if (availableSpaceInGB > 1) "ROM TEST : SUCCESS" else "ROM TEST : FAIL"
    } catch (error: Exception) {
        return "Error: ${error.message}"
    }
}
