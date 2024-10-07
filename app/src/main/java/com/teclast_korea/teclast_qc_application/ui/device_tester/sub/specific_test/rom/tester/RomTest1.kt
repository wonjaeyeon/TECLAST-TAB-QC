package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.rom.tester

import android.os.Environment
import android.os.StatFs
import com.teclast_korea.teclast_qc_application.domain.qc_result.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*

fun romTest1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
): String {
    try {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        val availableSpaceInGB = availableBlocks * blockSize / (1024 * 1024 * 1024)

        // You may adjust the threshold as per your requirement
        if (availableSpaceInGB > 1) {
            AddTestResult(state = state, onEvent = onEvent, "ROM TEST", "Success", Date().toString())
            return "ROM TEST : SUCCESS"
        } else {
            AddTestResult(state = state, onEvent = onEvent, "ROM TEST", "Fail", Date().toString())
            return "ROM TEST : FAIL"
        }
    } catch (error: Exception) {
        AddTestResult(state = state, onEvent = onEvent, "ROM TEST", "Fail", Date().toString())
        return "Error: ${error.message}"
    }
}
