package com.example.teclast_qc_application.test_result.test_results_db

import android.util.Log

fun CheckTestResultDB(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    itemName: String = ""
) {
    // Check the test result from the database
    onEvent(TestResultEvent.FindbyItemName(itemName))
    when (state) {
        is TestResultState -> {
            Log.i("CheckTestResultDB", "${state.testResults}")
        }
    }
}