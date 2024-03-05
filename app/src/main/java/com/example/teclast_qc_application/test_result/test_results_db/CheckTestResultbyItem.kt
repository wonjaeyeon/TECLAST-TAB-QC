package com.example.teclast_qc_application.test_result.test_results_db

fun CheckTestResultbyItem (
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
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