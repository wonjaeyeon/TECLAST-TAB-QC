package com.example.teclast_qc_application.test_result.test_results_db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TestResultViewModel(
    private val dao: TestResultDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.ITEM_NAME)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.ITEM_NAME -> dao.getTestResultsOrderedByFirstName()
                SortType.TEST_RESULT -> dao.getTestResultsOrderedByLastName()
                SortType.TEST_DATE -> dao.getTestResultsOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(TestResultState())
    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            testResults = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TestResultState())

    fun onEvent(event: TestResultEvent) {
        when(event) {
            is TestResultEvent.DeleteTestResult -> {
                viewModelScope.launch {
                    dao.deleteTestResult(event.contact)
                }
            }
            TestResultEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingContact = false
                ) }
            }
            TestResultEvent.SaveTestResult -> {
                val firstName = state.value.itemName
                val lastName = state.value.testResult
                val phoneNumber = state.value.testDate

                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                val contact = TestResult(
                    itemName = firstName,
                    testResult = lastName,
                    testDate = phoneNumber
                )
                viewModelScope.launch {
                    dao.upsertTestResult(contact)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    itemName = "",
                    testResult = "",
                    testDate = ""
                ) }
            }
            is TestResultEvent.SetItemName -> {
                _state.update { it.copy(
                    itemName = event.itemName
                ) }
            }
            is TestResultEvent.SetTestResult -> {
                _state.update { it.copy(
                    testResult = event.testResult
                ) }
            }
            is TestResultEvent.SetTestDate -> {
                _state.update { it.copy(
                    testDate = event.testDate
                ) }
            }
            TestResultEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }
            is TestResultEvent.SortContacts -> {
                _sortType.value = event.sortType
            }
        }
    }
}