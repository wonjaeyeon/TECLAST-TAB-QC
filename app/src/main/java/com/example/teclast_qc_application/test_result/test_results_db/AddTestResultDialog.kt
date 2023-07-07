package com.example.teclast_qc_application.test_result.test_results_db

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Screen that allows the user to add a new contact to the database.
@Composable
fun AddTestResultDialog(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(TestResultEvent.HideDialog)
        },
        title = { Text(text = "Add Test Result") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.itemName,
                    onValueChange = {
                        onEvent(TestResultEvent.SetItemName(it))
                    },
                    placeholder = {
                        Text(text = "Item name")
                    }
                )
                TextField(
                    value = state.testResult,
                    onValueChange = {
                        onEvent(TestResultEvent.SetTestResult(it))
                    },
                    placeholder = {
                        Text(text = "Test result")
                    }
                )
                TextField(
                    value = state.testDate,
                    onValueChange = {
                        onEvent(TestResultEvent.SetTestDate(it))
                    },
                    placeholder = {
                        Text(text = "Test date")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(TestResultEvent.SaveTestResult)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}