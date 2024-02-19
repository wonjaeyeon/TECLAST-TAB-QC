package com.example.teclast_qc_application.device_tester.specific_test.ram.tester

import com.example.teclast_qc_application.test_result.test_results_db.AddTestResultV2
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
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
                AddTestResultV2(state = state, onEvent = onEvent, "RAM TEST", "Fail", Date().toString())
                return "RAM TEST: Failed"
            }
        }
        AddTestResultV2(state = state, onEvent = onEvent, "RAM TEST", "Success", Date().toString())
        "RAM TEST: Success"
    } catch (error: Exception) {
        AddTestResultV2(state = state, onEvent = onEvent, "RAM TEST", "Fail", Date().toString())
        "Error: ${error.message}"
    }
}
