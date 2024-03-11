package com.example.teclast_qc_application.device_tester.standard_test.fast_mode.sub_screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Unpublished
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FastTestFailedScreen(
    context: Context,
    navController: NavHostController,
    onEvent: (TestResultEvent) -> Unit,
    state: TestResultState,
    onExitApp: () -> Unit,
    //deviceSpec: List<Pair<String, String>>
) {

// Generate PDF and handle related events as soon as the screen is composed
//    LaunchedEffect(Unit) {
//        onEvent(TestResultEvent.SaveTestResult)
//        onEvent(TestResultEvent.ClearPreviousTestResults)
//
//        async {
//            generatePDF(
//                context = context,
//                directory = getDirectory(context),
//                state = state,
//                onEvent = onEvent,
//                deviceSpec = deviceSpec
//            )
//        }
//    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Test Failed") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,

                )
        }
    ) {
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
                    // Pop back to the root of the navigation stack, effectively clearing it.
                    Toast.makeText(context, "Fast Mode Test Finished", Toast.LENGTH_SHORT).show()
                    navController.popBackStack(navController.graph.startDestinationId, false)
                },
                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
            ) {
                Text("Go Back to Main Page")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {

//                    Toast.makeText(context, "PDF file generated successfully", Toast.LENGTH_SHORT).show()
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
