package com.example.teclast_qc_application.device_tester.standard_test.api_kit

import android.content.Context
import android.util.Log
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
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState

@Composable
fun SkipToNextTestDialog(
    context: Context,
    navController: NavController,
    nextTestRoute : MutableList<String>,
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    showDeleteAlertDialog : MutableState<Boolean>,
    deviceSpec : List<Pair<String,String>>,
    testMode: String

) {
    AlertDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = {
            showDeleteAlertDialog.value = false
        },
        title = { Text("Skip Test") },
        text = { Text("Are you sure you want to Skip to next test?") },
        confirmButton = {
            Button(
                border = BorderStroke(1.dp, Color.Green),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Green,

                    ),
                onClick = {
                    // Pop back stack
                    Toast.makeText(context, "Test Is Skipped", Toast.LENGTH_SHORT).show()
                    showDeleteAlertDialog.value = false
                    onEvent(TestResultEvent.SaveTestResult)
                    onEvent(TestResultEvent.ClearPreviousTestResults)
                    // put navigation that navigate to next test
                    if (nextTestRoute.isNotEmpty()) {
                        // Test Mode인 경우
                        // nextTestRoute : 바로 다음 경로 + 그 다음 경로들 포함
                        // 예) nextTestRoute = ["test1", "test2", "test3"] 이면
                        // 바로 경로 : test1
                        // 다음 경로 : test2 -> test3
                        // nextPathString : test2 -> test3
                        val pastRoute = nextTestRoute.removeAt(0)
                        val nextRoute = nextTestRoute[0]
                        val nextPath = nextTestRoute.drop(1)
                        val nextPathString = nextPath.joinToString(separator = "->")
                        var nextRouteWithArguments = ""
                        if (nextPathString.isNotEmpty()) {
                            nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
                            Log.i("JumpToNextTest", "nextRouteWithArguments: $nextRouteWithArguments")
                        } else {
                            nextRouteWithArguments = "${nextTestRoute[0]}"
                            Log.i("JumpToNextTest", "nextRouteWithArguments: $nextRouteWithArguments")
                        }

                        navController.navigate(nextRouteWithArguments)
                    }

                }
            ) {
                Text("Skip Test")
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