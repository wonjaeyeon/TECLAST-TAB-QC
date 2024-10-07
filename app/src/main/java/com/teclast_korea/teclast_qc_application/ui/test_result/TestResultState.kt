package com.teclast_korea.teclast_qc_application.ui.test_result

import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.SortType
import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.TotalQCResult

data class TestResultState(
    val totalQCResults: List<TotalQCResult> = emptyList(),
    val itemName: String = "",
    val testResult: String = "",
    val testDate: String = "",
    val isAddingContact: Boolean = false,
    val isDeletingAllContacts: Boolean = false,
    val isTestModeRunning: Boolean = false,
    val sortType: SortType = SortType.ITEM_NAME
)
