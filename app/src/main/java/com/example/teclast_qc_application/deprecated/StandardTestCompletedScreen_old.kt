package com.example.teclast_qc_application.deprecated

//import android.annotation.SuppressLint
//import android.content.Context
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.TaskAlt
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.teclast_qc_application.home.device_report.DeviceSpecReportList
//import com.example.teclast_qc_application.home.pdf_export.generate_pdf.generatePDF
//import com.example.teclast_qc_application.home.pdf_export.getDirectory
//import com.example.teclast_qc_application.home.test_report.TestReportList
//import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
//import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
//import java.io.File
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun StandardTestCompletedScreenOld(
//    context: Context,
//    state: TestResultState,
//    navController: NavHostController,
//    onEvent: (TestResultEvent) -> Unit,
//    onExitApp: () -> Unit
//) {
//    val device_spec_pdf = DeviceSpecReportList(context)
//
////    LaunchedEffect(key1 = true) {
////        withContext(Dispatchers.IO) {
////            Log.i("StandardTestCompletedScreen", "Checking how much this Logic is called = LaunchedEffect(key1 = true)")
////            val File = File(getDirectory(context), "Test_Report.pdf")
////            if (File.exists()) {
////                File.delete()
////            }
//////            withContext(Dispatchers.Main) {
//////                Toast.makeText(context, "Test Is Stopped", Toast.LENGTH_SHORT).show()
//////            }
////            onEvent(TestResultEvent.SaveTestResult)
////            onEvent(TestResultEvent.ClearPreviousTestResults)
////            //CoroutineScope(Dispatchers.IO).async {
////            // Generate PDF
//////            withContext(Dispatchers.Main) {
//////                Toast.makeText(context, "Generating Report", Toast.LENGTH_SHORT).show()
//////            }
////
////            val testReportList = TestReportList(context = context, state = state, onEvent = onEvent)
////
////            generatePDF(
////                context = context,
////                directory = getDirectory(context),
////                state = state,
////                onEvent = onEvent,
////                deviceSpec = device_spec_pdf,
////                testMode = "StandardMode",
////                testReportList = testReportList,
////            )
//////            withContext(Dispatchers.Main) {
//////                Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
//////            }
////        }
////    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Test Completed") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = MaterialTheme.colors.onPrimary,
//
//                )
//        }
//    ) {
//        onEvent(TestResultEvent.SaveTestResult)
//        onEvent(TestResultEvent.ClearPreviousTestResults)
//        onEvent(TestResultEvent.SaveTestResult)
//        Log.i("StandardTestCompletedScreen_TestDB", TestReportList(context = context, state = state, onEvent = onEvent).toString())
//        Log.i("StandardTestCompletedScreen", "Checking how much this Logic is called = in scaffold")
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxSize()
//        ) {
//            Icon(
//                imageVector = Icons.Filled.TaskAlt,
//                contentDescription = "Completed",
//                tint = Color.Green,
//                modifier = Modifier.size(120.dp)
//            )
//            Text(text = "Test Finished", style = MaterialTheme.typography.h4)
//            Spacer(modifier = Modifier.height(32.dp))
//            Button(
//                onClick = {
//                    onEvent(TestResultEvent.ClearPreviousTestResults)
//                    // Pop back to the root of the navigation stack, effectively clearing it.
//                    Log.i("StandardTestCompletedScreen_TestDB", TestReportList(context = context, state = state, onEvent = onEvent).toString()) // TODO : this is the safest place and most logical place : 괜히 디스코드 인스타 꼼수 따라하지 말자.
//
//                    val File = File(getDirectory(context), "Test_Report.pdf")
//                    if (File.exists()) {
//                        File.delete()
//                    }
//
//                    //CoroutineScope(Dispatchers.IO).async {
//
//                    val testReportList = TestReportList(context = context, state = state, onEvent = onEvent)
//
//                    generatePDF(
//                        context = context,
//                        directory = getDirectory(context),
//                        state = state,
//                        onEvent = onEvent,
//                        deviceSpec = device_spec_pdf,
//                        testMode = "StandardMode",
//                        testReportList = testReportList,
//                    )
//
//                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
//                    navController.popBackStack(navController.graph.startDestinationId, false)
//                },
//
//                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
//            ) {
//                Text("Go Back to Main Page")
//            }
//            Spacer(modifier = Modifier.height(32.dp))
//            Button(
//                onClick = {
//                    onEvent(TestResultEvent.ClearPreviousTestResults)
//
//                    onEvent(TestResultEvent.ClearPreviousTestResults)
//                    // Pop back to the root of the navigation stack, effectively clearing it.
//                    Log.i("StandardTestCompletedScreen_TestDB", TestReportList(context = context, state = state, onEvent = onEvent).toString()) // TODO : this is the safest place and most logical place : 괜히 디스코드 인스타 꼼수 따라하지 말자.
//
//                    val File = File(getDirectory(context), "Test_Report.pdf")
//                    if (File.exists()) {
//                        File.delete()
//                    }
//
//                    val testReportList = TestReportList(context = context, state = state, onEvent = onEvent)
//
//                    generatePDF(
//                        context = context,
//                        directory = getDirectory(context),
//                        state = state,
//                        onEvent = onEvent,
//                        deviceSpec = device_spec_pdf,
//                        testMode = "StandardMode",
//                        testReportList = testReportList,
//                    )
//
//                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
//
//
//
//                    onExitApp()
//                },
//                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth(),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green, contentColor = MaterialTheme.colors.onBackground)
//            ) {
//                Text("Save Test Result & Exit Application")
//            }
//        }
//    }
//}
