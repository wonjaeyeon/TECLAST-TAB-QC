package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.ram.tester

import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultState
import java.util.*

fun ramTest1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    arraySize: Int = 100000
): String {
    return try {
        val random = Random()
        val originalData = IntArray(arraySize)
        val verifyTestData = IntArray(arraySize)

        // Fill the array with random data
        for (i in originalData.indices) {
            originalData[i] = random.nextInt()
            verifyTestData[i] = originalData[i]
        }

        // Verify the data in the array
        for (i in originalData.indices) {
            if (originalData[i] != verifyTestData[i]) {
                AddTestResult(state = state, onEvent = onEvent, "RAM TEST", "Fail", Date().toString())
                return "RAM TEST: Fail"
            }
        }
        AddTestResult(state = state, onEvent = onEvent, "RAM TEST", "Success", Date().toString())
        "RAM TEST: Success"
    } catch (error: Exception) {
        AddTestResult(state = state, onEvent = onEvent, "RAM TEST", "Fail", Date().toString())
        "RAM TEST: Fail : Error: ${error.message}"
    }
}
