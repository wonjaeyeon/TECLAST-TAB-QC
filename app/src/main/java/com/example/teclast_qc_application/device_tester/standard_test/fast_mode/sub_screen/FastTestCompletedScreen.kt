package com.example.teclast_qc_application.device_tester.standard_test.fast_mode.sub_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FastTestCompletedScreen(
    context: Context,
    navController: NavHostController,
    onEvent: (TestResultEvent) -> Unit,
    onExitApp: () -> Unit
) {
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
                    onExitApp()
                },
                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
            ) {
                Text("Save Test Result & Exit Application")
            }
        }
    }
}
