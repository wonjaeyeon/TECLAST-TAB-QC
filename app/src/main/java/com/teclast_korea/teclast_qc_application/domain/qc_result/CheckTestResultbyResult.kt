package com.teclast_korea.teclast_qc_application.domain.qc_result

import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState

fun CheckTestResultbyResult (
    state: TestResultState
): String {
    val testResult = state.totalQCResults.filter { it.testResult.contains("Fail" ) }
    if (testResult.isNotEmpty()) {
        return "FAIL"
    }
    return "PASS"
}