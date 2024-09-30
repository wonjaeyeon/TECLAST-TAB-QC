package com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db

import com.teclast_korea.teclast_qc_application.data.qc_results.SortType
import com.teclast_korea.teclast_qc_application.data.qc_results.TestResult

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
