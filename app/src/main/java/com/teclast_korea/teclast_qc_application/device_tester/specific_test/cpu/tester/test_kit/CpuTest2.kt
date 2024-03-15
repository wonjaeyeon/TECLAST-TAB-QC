package com.teclast_korea.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit

import android.util.Log
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import java.nio.ByteBuffer
import java.util.*

fun cpuBufferTest(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    bufferSize: Int = 4024
): String {
    return try {
        val random = Random()
        val originalData = ByteArray(bufferSize)
        random.nextBytes(originalData)

        val buffer = ByteBuffer.allocate(bufferSize)
        buffer.put(originalData)
        buffer.flip()

        val readData = ByteArray(bufferSize)
        buffer.get(readData)

        // Clear the buffer after read
        buffer.clear()

        if (originalData.contentEquals(readData)) {
            AddTestResult(state = state, onEvent = onEvent, "CPU BUFFER TEST", "Success", Date().toString())
            Log.i("CPU BUFFER TEST", "Success & ${Date()}")
            "CPU BUFFER TEST: Success"
        } else {
            AddTestResult(state = state, onEvent = onEvent, "CPU BUFFER TEST", "Fail", Date().toString())
            "CPU BUFFER TEST: Failed"
        }

    } catch (error: Exception) {
        "Error: ${error.message}"
    }
}