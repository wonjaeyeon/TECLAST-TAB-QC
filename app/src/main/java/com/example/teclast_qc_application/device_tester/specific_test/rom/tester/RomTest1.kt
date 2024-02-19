package com.example.teclast_qc_application.device_tester.specific_test.rom.tester

import android.os.Environment
import android.os.StatFs
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResultV2
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
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
            AddTestResultV2(state = state, onEvent = onEvent, "ROM TEST", "Success", Date().toString())
            return "ROM TEST : SUCCESS"
        } else {
            AddTestResultV2(state = state, onEvent = onEvent, "ROM TEST", "Fail", Date().toString())
            return "ROM TEST : FAIL"
        }
    } catch (error: Exception) {
        AddTestResultV2(state = state, onEvent = onEvent, "ROM TEST", "Fail", Date().toString())
        return "Error: ${error.message}"
    }
}
