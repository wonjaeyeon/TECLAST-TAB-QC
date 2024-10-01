package com.teclast_korea.teclast_qc_application.ui.test_result.test_results

fun CheckTestResultbyResult (
    state: TestResultState
): String {
    val testResult = state.testResults.filter { it.testResult.contains("Fail" ) }
    if (testResult.isNotEmpty()) {
        return "FAIL"
    }
    return "PASS"
}