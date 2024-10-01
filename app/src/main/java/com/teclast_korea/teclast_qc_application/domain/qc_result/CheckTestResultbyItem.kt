package com.teclast_korea.teclast_qc_application.domain.qc_result

import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState

fun CheckTestResultbyItem (
    state: TestResultState,
    itemName: String = ""
): String {
    val testResult = state.testResults.filter { it.itemName.contains(itemName) }
    if (testResult.isEmpty()) {
        return "No Test Result"
    }
    for (result in testResult) {
        if (result.testResult == "Fail") {
            return "FAIL"
        }
    }
    return "PASS"
}