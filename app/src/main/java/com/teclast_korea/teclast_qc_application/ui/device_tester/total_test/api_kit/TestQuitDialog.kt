package com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit

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
import com.teclast_korea.teclast_qc_application.ui.home.pdf_export.getDirectory
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultEvent
import java.io.File

@Composable
fun QuitTestDialog(
    context: Context,
    navController: NavController,
    onEvent: (TestResultEvent) -> Unit,
    showDeleteAlertDialog: MutableState<Boolean>,
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
//                    Toast.makeText(context, "Generating Report", Toast.LENGTH_SHORT).show()
//                    try {
//                        CoroutineScope(Dispatchers.IO).async {

//                    val testReportList = TestReportList(context = context, state = state, onEvent = onEvent)
//
//                    generatePDF(
//                        context = context,
//                        directory = getDirectory(context),
//                        state = state,
//                        onEvent = onEvent,
//                        deviceSpec = deviceSpec,
//                        testMode = testMode,
//                        testReportList = testReportList,
//                    )

//                        }
//                    } catch (e: Exception) {
//                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
//                    }

 //                   Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()


                    var deviceNavID = ""
                    when (testMode) {
                        "FastMode" -> {
                            deviceNavID = "fast_test_fail_screen"
                        }
                        "TOrderMode" -> {
                            deviceNavID = "t_order_test_fail_screen"
                        }
                        "SCSPROMode" -> {
                            deviceNavID = "scspro_test_fail_screen"
                        }
                    }
                    navController.navigate("$deviceNavID/$testMode")
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