package com.example.teclast_qc_application.device_tester.standard_test.api_kit

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
import androidx.navigation.NavController
import com.example.teclast_qc_application.home.pdf_export.generate_pdf.generatePDF
import com.example.teclast_qc_application.home.pdf_export.getDirectory
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import java.io.File

@Composable
fun QuitTestDialog(
    context: Context,
    navController: NavController,
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    showDeleteAlertDialog: MutableState<Boolean>,
    deviceSpec: List<Pair<String, String>>,
    testMode: String
) {
    AlertDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = {
            showDeleteAlertDialog.value = false
        },
        title = { Text("Quit Test") },
        text = { Text("Are you sure you want to quit the test?") },
        confirmButton = {
            Button(
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Red,

                    ),
                onClick = {
                    // Pop back stack
                    val File = File(getDirectory(context), "Test_Report.pdf")
                    if (File.exists()) {
                        File.delete()
                    }
                    Toast.makeText(context, "Test Is Stopped", Toast.LENGTH_SHORT).show()
                    showDeleteAlertDialog.value = false
                    onEvent(TestResultEvent.SaveTestResult)
                    onEvent(TestResultEvent.ClearPreviousTestResults)
                    Toast.makeText(context, "Generating Report", Toast.LENGTH_SHORT).show()
//                    try {
//                        CoroutineScope(Dispatchers.IO).async {
                    generatePDF(
                        context = context,
                        directory = getDirectory(context),
                        state = state,
                        onEvent = onEvent,
                        deviceSpec = deviceSpec,
                        testMode = testMode,
                    )
//                        }
//                    } catch (e: Exception) {
//                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
//                    }

                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
                    navController.navigate("fast_test_fail_screen/$testMode")
                }
            ) {
                Text("Quit Test")
            }
        },
        dismissButton = {
            Button(onClick = {
                showDeleteAlertDialog.value = false
            }) {
                Text("Cancel")
            }
        }
    )
}