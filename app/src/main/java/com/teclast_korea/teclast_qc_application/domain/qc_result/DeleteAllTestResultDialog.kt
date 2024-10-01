package com.teclast_korea.teclast_qc_application.domain.qc_result

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.teclast_korea.teclast_qc_application.domain.qc_report.pdf_export.getDirectory
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.io.File

@Composable
fun DeleteAllTestResultDialog(
    context: Context,
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    showDeleteAlertDialog: MutableState<Boolean>

) {
    AlertDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = {
        },
        title = { Text("Delete All Test Results") },
        text = { Text("Are you sure you want to delete all test results & PDF Report file? This action cannot be undone.") },
        confirmButton = {
            Button(
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Red,

                    ),
                onClick = {
                    val file = File(getDirectory(context), "Test_Report.pdf")

                    if (state.testResults.isEmpty()) {
                        Toast.makeText(context, "No Test Results to delete", Toast.LENGTH_SHORT).show()
                        showDeleteAlertDialog.value = false
                    } else {
                        onEvent(TestResultEvent.DeleteAllTestResults)
                        Toast.makeText(context, "Test Results Deleted", Toast.LENGTH_SHORT).show()
                        showDeleteAlertDialog.value = false
                    }
                    if (file.exists()) {
                        file.delete()
                        Toast.makeText(context, "Report File Deleted", Toast.LENGTH_SHORT).show()
                    }

                }) {
                Text("Delete All")
            }
        },
        dismissButton = {
            Button(onClick = {
                showDeleteAlertDialog.value = false
            }) {
                onEvent(TestResultEvent.HideDeleteAllDialog)
                Text("Cancel")
            }
        }
    )
}