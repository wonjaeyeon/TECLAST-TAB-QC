package com.example.teclast_qc_application.test_result.test_results_db

data class TestResultState(
    val testResults: List<TestResult> = emptyList(),
    val itemName: String = "",
    val testResult: String = "",
    val testDate: String = "",
    val isAddingContact: Boolean = false,
    val isDeletingAllContacts: Boolean = false,
    val isTestModeRunning: Boolean = false,
    val sortType: SortType = SortType.ITEM_NAME
)
