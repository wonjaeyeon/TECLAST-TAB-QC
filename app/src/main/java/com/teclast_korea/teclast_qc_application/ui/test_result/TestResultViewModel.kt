package com.teclast_korea.teclast_qc_application.ui.test_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.SortType
import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.TotalQCResult
import com.teclast_korea.teclast_qc_application.data.qc_result.datasource.local.TotalQCResultDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TestResultViewModel(
    private val dao: TotalQCResultDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.ITEM_NAME)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.ITEM_NAME -> dao.getTestResultsOrderedByFirstName()
                SortType.TEST_RESULT -> dao.getTestResultsOrderedByLastName()
                SortType.TEST_DATE -> dao.getTestResultsOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(TestResultState())
    val state = combine(_state, _sortType, _contacts) { state, sortType, testResults ->
        state.copy(
            totalQCResults = testResults,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TestResultState())

    // TODO : 여기에 이전에 어떤 항목을 추가했는지에 대한 기억 변수를 만들고 만약 이전에 추가한 적이 있다면 다시 추가 안 하도록 로직을 추가하면 되지 않을까?

    fun onEvent(event: TestResultEvent) {
        when (event) {
            is TestResultEvent.DeleteTestResult -> {
                viewModelScope.launch {
                    dao.deleteTestResult(event.contact)
                }
            }

            is TestResultEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            is TestResultEvent.SaveTestResult -> {
                val firstName = state.value.itemName
                val lastName = state.value.testResult
                val phoneNumber = state.value.testDate

                if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }


                val contact = TotalQCResult(
                    itemName = firstName,
                    testResult = lastName,
                    testDate = phoneNumber
                )
                viewModelScope.launch {
                    // if the contact already exists, delete it and then insert the new contact
                    if (dao.countTestResultByItemName(contact.itemName) > 0) {
                        val existingContact = dao.getTestResultByItemName(contact.itemName)
                        if (existingContact != null) {
                            dao.deleteTestResult(existingContact)
                        }
                    }
                    dao.upsertTestResult(contact)
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        itemName = "",
                        testResult = "",
                        testDate = ""
                    )
                }
            }

            is TestResultEvent.SetItemName -> {
                _state.update {
                    it.copy(
                        itemName = event.itemName
                    )
                }
            }

            is TestResultEvent.SetTestResult -> {
                _state.update {
                    it.copy(
                        testResult = event.testResult
                    )
                }
            }

            is TestResultEvent.SetTestDate -> {
                _state.update {
                    it.copy(
                        testDate = event.testDate
                    )
                }
            }


            is TestResultEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }

            is TestResultEvent.ShowDeleteAllDialog -> {
                _state.update {
                    it.copy(
                        isDeletingAllContacts = true
                    )
                }
            }

            is TestResultEvent.HideDeleteAllDialog -> {
                _state.update {
                    it.copy(
                        isDeletingAllContacts = false
                    )
                }
            }

            is TestResultEvent.StartTest -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }

            is TestResultEvent.EndTest -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            is TestResultEvent.SortContacts -> {
                _sortType.value = event.sortType
            }

            is TestResultEvent.DeleteAllTestResults -> {
                viewModelScope.launch {
                    dao.deleteAllTestResults()
                }
            }

            is TestResultEvent.ClearPreviousTestResults -> {
                viewModelScope.launch {
                    dao.cleanUpPreviousTestResults()
                }
            }

        }
    }
}