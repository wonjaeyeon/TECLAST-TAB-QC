package com.example.teclast_qc_application.device_tester.standard_test.standard_mode.sub_screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.home.device_report.DeviceSpecReportList
import com.example.teclast_qc_application.home.pdf_export.generate_pdf.generatePDF
import com.example.teclast_qc_application.home.pdf_export.getDirectory
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StandardTestCompletedScreen(
    context: Context,
    state: TestResultState,
    navController: NavHostController,
    onEvent: (TestResultEvent) -> Unit,
    onExitApp: () -> Unit
) {
    val device_spec_pdf = DeviceSpecReportList(context)

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            val File = File(getDirectory(context), "Test_Report.pdf")
            if (File.exists()) {
                File.delete()
            }
//            withContext(Dispatchers.Main) {
//                Toast.makeText(context, "Test Is Stopped", Toast.LENGTH_SHORT).show()
//            }
            onEvent(TestResultEvent.SaveTestResult)
            onEvent(TestResultEvent.ClearPreviousTestResults)
            //CoroutineScope(Dispatchers.IO).async {
            // Generate PDF
//            withContext(Dispatchers.Main) {
//                Toast.makeText(context, "Generating Report", Toast.LENGTH_SHORT).show()
//            }

            generatePDF(
                context = context,
                directory = getDirectory(context),
                state = state,
                onEvent = onEvent,
                deviceSpec = device_spec_pdf,
                testMode = "StandardMode",
                showToast = false
            )
//            withContext(Dispatchers.Main) {
//                Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Test Completed") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,

                )
        }
    ) {
        onEvent(TestResultEvent.SaveTestResult)
        onEvent(TestResultEvent.ClearPreviousTestResults)
        onEvent(TestResultEvent.SaveTestResult)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.TaskAlt,
                contentDescription = "Completed",
                tint = Color.Green,
                modifier = Modifier.size(120.dp)
            )
            Text(text = "Test Finished", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    onEvent(TestResultEvent.ClearPreviousTestResults)
                    // Pop back to the root of the navigation stack, effectively clearing it.
                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
                    navController.popBackStack(navController.graph.startDestinationId, false)
                },

                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
            ) {
                Text("Go Back to Main Page")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    onEvent(TestResultEvent.ClearPreviousTestResults)
//                    val activity: MainActivity = MainActivity()
//                    // on below line we are finishing activity.
//                    activity.finishAndRemoveTask()
//                    exitProcess(0)
                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
                    onExitApp()
                },
                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
            ) {
                Text("Save Test Result & Exit Application")
            }
        }
    }
}
