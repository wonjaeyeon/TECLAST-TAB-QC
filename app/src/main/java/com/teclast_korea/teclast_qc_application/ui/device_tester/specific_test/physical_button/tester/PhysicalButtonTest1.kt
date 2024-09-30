package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.physical_button.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit.DialogAPIInterface
import com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit.TestAPIDialog
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultState
import kotlinx.coroutines.delay
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun PhysicalButtonTestT1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    volumeUpPressed: MutableState<Boolean>,
    volumeDownPressed: MutableState<Boolean>,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {

    val showDialog = remember { mutableStateOf(false) }

    // Observe volumeUpPressed and volumeDownPressed.
    // If either is true, navigate to the previous screen.
    LaunchedEffect(volumeUpPressed.value, volumeDownPressed.value) {
        if (volumeUpPressed.value && volumeDownPressed.value) {
            // navController.popBackStack()
            onEvent(TestResultEvent.SaveTestResult)
            AddTestResult(
                state = state,
                onEvent = onEvent,
                "Physical Button Test 1",
                "Success",
                Date().toString()
            )
            onEvent(TestResultEvent.SaveTestResult)
            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0)
                Log.i("MyTag:PhysicalButtonTestT1", "pastRoute: $pastRoute")
                Log.i("MyTag:PhysicalButtonTestT1", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "->")
                Log.i("MyTag:PhysicalButtonTestT1", "nextPath: $nextPath")
                Log.i("MyTag:PhysicalButtonTestT1", "nextPathString: $nextPathString")

                var nextRouteWithArguments: String
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
                    Log.i("Check", "nextRouteWithArguments: $nextRouteWithArguments")
                } else {
                    nextRouteWithArguments = nextRoute
                }

                navController.navigate(nextRouteWithArguments)
            }
            else {
                Log.i("MyTag:PhysicalButtonTestT1", "delay(1000)")
                delay(1000)
                navController.popBackStack()
            }
        } else {
            onEvent(TestResultEvent.SaveTestResult)
            AddTestResult(
                state = state,
                onEvent = onEvent,
                "Physical Button Test 1",
                "Fail",
                Date().toString()
            )
            onEvent(TestResultEvent.SaveTestResult)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Physical Button Test 1") },
                navigationIcon = {
                    NavigationPopButton(
                        navController = navController, testMode = testMode
                    )
                },
                actions = {
                    DialogAPIInterface(
                        testMode = testMode,
                        showDialog = showDialog
                    )
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },

        ) {
        TestAPIDialog(
            testMode = testMode,
            onEvent = onEvent,
            context = context,
            navController = navController,
            nextTestRoute = nextTestRoute,
            showDialog = showDialog
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Press Volume Up/Down button to test", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(16.dp))
                Log.i(volumeUpPressed.value.toString(), "Volume Up Pressed")
                Log.i(volumeDownPressed.value.toString(), "Volume Down Pressed")
                if (volumeUpPressed.value == true) {
                    Text(text = "Volume Up button pressed", style = MaterialTheme.typography.h5)
                }
                if (volumeDownPressed.value == true) {
                    Text(text = "Volume Down button pressed", style = MaterialTheme.typography.h5)
                }

            }
        }
    }


}
