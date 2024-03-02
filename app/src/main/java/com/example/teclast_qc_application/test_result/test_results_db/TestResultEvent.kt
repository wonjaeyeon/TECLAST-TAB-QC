package com.example.teclast_qc_application.test_result.test_results_db

sealed interface TestResultEvent {
    object SaveTestResult: TestResultEvent
    data class SetItemName(val itemName: String): TestResultEvent
    data class SetTestResult(val testResult: String): TestResultEvent
    data class SetTestDate(val testDate: String): TestResultEvent
    object ShowDialog: TestResultEvent
    object HideDialog: TestResultEvent

    object StartTest: TestResultEvent
    object EndTest: TestResultEvent
    data class SortContacts(val sortType: SortType): TestResultEvent
    data class DeleteTestResult(val contact: TestResult): TestResultEvent

}
