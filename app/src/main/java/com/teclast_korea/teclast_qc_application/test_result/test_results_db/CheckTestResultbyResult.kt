package com.teclast_korea.teclast_qc_application.test_result.test_results_db

fun CheckTestResultbyResult (
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit
): String {
    val testResult = state.testResults.filter { it.testResult.contains("Fail" ) }
    if (testResult.isNotEmpty()) {
        return "FAIL"
    }
    return "PASS"
}