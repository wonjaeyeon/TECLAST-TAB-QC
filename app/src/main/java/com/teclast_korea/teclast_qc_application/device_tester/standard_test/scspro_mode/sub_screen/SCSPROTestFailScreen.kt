package com.teclast_korea.teclast_qc_application.device_tester.standard_test.scspro_mode.sub_screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Unpublished
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teclast_korea.teclast_qc_application.home.device_report.DeviceSpecReportList
import com.teclast_korea.teclast_qc_application.home.pdf_export.generate_pdf.generatePDF
import com.teclast_korea.teclast_qc_application.home.pdf_export.getDirectory
import com.teclast_korea.teclast_qc_application.home.test_report.TestReportList
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SCSPROTestFailedScreen(
    context: Context,
    navController: NavHostController,
    onEvent: (TestResultEvent) -> Unit,
    state: TestResultState,
    onExitApp: () -> Unit,
) {

    val isLoading = remember { mutableStateOf(false) }
    val currentLoadingPage = remember { mutableDoubleStateOf(0.0) }
    val pageCount = remember { mutableDoubleStateOf(5.0) }

    val device_spec_pdf = DeviceSpecReportList(context)


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Test Failed") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,

                )
        }
    ) {
//        Log.i(
//            "FailTestFailScreen_TestDB-inScaffold",
//            TestReportList(context = context, state = state, onEvent = onEvent).toString()
//        )
        onEvent(TestResultEvent.SaveTestResult)
        onEvent(TestResultEvent.ClearPreviousTestResults)
        onEvent(TestResultEvent.SaveTestResult)

        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading.value) {
                Column(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary),
                    verticalArrangement = Arrangement.Center,

                    ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        color = MaterialTheme.colors.primaryVariant,
                        progress = currentLoadingPage.doubleValue.toFloat() / pageCount.doubleValue.toFloat()
                    )
                    Text(
                        color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 5.dp)
                            .padding(horizontal = 30.dp),

                        text = "Generating Report... ${currentLoadingPage.doubleValue.toFloat() / pageCount.doubleValue.toFloat() * 100}%"
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Unpublished,
                        contentDescription = "Failed",
                        tint = Color.Red,
                        modifier = Modifier.size(120.dp)
                    )
                    Text(text = "Test Failed", style = MaterialTheme.typography.h4)
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            isLoading.value = true // Show loading indicator

                            currentLoadingPage.value += 1.0

                            onEvent(TestResultEvent.ClearPreviousTestResults)
                            // Pop back to the root of the navigation stack, effectively clearing it.
                            Log.i(
                                "SCSPROTestFailScreen_TestDB",
                                TestReportList(context = context, state = state, onEvent = onEvent).toString()
                            ) // TODO : this is the safest place and most logical place : 괜히 디스코드 인스타 꼼수 따라하지 말자.

                            currentLoadingPage.value += 1.0

                            CoroutineScope(Dispatchers.IO).launch {

                                val File = File(getDirectory(context), "Test_Report.pdf")
                                if (File.exists()) {
                                    File.delete()
                                }

                                currentLoadingPage.value += 1.0

                                val testReportList = TestReportList(context = context, state = state, onEvent = onEvent)

                                currentLoadingPage.value += 1.0

                                generatePDF(
                                    context = context,
                                    directory = getDirectory(context),
                                    state = state,
                                    onEvent = onEvent,
                                    deviceSpec = device_spec_pdf,
                                    testMode = "SCSPROMode",
                                    testReportList = testReportList,
                                )
                                currentLoadingPage.value += 1.0

                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()

                                    isLoading.value = false // Hide loading indicator
                                }
                            }
                            navController.popBackStack(navController.graph.startDestinationId, false)
                        },
                        modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
                    ) {
                        Text("Go Back to Main Page")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            isLoading.value = true // Show loading indicator

                            currentLoadingPage.value += 1.0

                            onEvent(TestResultEvent.ClearPreviousTestResults)
                            // Pop back to the root of the navigation stack, effectively clearing it.
                            Log.i(
                                "SCSPROTestFailScreen_TestDB",
                                TestReportList(context = context, state = state, onEvent = onEvent).toString()
                            ) // TODO : this is the safest place and most logical place : 괜히 디스코드 인스타 꼼수 따라하지 말자.

                            currentLoadingPage.value += 1.0

                            CoroutineScope(Dispatchers.IO).launch {


                                val File = File(getDirectory(context), "Test_Report.pdf")
                                if (File.exists()) {
                                    File.delete()
                                }

                                currentLoadingPage.value += 1.0

                                val testReportList = TestReportList(context = context, state = state, onEvent = onEvent)

                                currentLoadingPage.value += 1.0

                                generatePDF(
                                    context = context,
                                    directory = getDirectory(context),
                                    state = state,
                                    onEvent = onEvent,
                                    deviceSpec = device_spec_pdf,
                                    testMode = "SCSPROMode",
                                    testReportList = testReportList,
                                )
                                currentLoadingPage.value += 1.0

                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()

                                    isLoading.value = false // Hide loading indicator
                                    onExitApp()
                                }
                            }
                        },
                        modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Green,
                            contentColor = MaterialTheme.colors.onBackground
                        )
                    ) {
                        Text("Save Test Result & Exit Application")
                    }
                }
            }
        }
    }
}
