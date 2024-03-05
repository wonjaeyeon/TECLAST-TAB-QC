package com.example.teclast_qc_application.device_tester.specific_test.vibration.tester

import android.annotation.SuppressLint
import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VibrationTestTestMode(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    testMode: String = "StandardMode",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    var vibrationResult = remember { mutableStateOf("Ready for Test") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Vibration Test") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FloatingActionButton(
                    modifier = Modifier.padding(start = 16.dp),
                    // add color to the background
                    backgroundColor = Color(0xFF00FF00),

                    onClick = { /* Handle success result */
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "Vibration Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:VibrationTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:VibrationTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:VibrationTest1", "nextPath: $nextPath")
                            Log.i("MyTag:VibrationTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = "aaaa"
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
                            }

                            navController.navigate(nextRouteWithArguments)
                        } else if (runningTestMode)
                            onTestComplete()
                        else
                            navController.popBackStack()
                    }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "Vibration Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:VibrationTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:VibrationTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:VibrationTest1", "nextPath: $nextPath")
                            Log.i("MyTag:VibrationTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = "aaaa"
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
                            }

                            navController.navigate(nextRouteWithArguments)
                        } else if (runningTestMode)
                            onTestComplete()
                        else
                            navController.popBackStack()
                    }) {
                    Text("Fail")
                }
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    if (vibrator.hasVibrator()) { // Check if device has vibrator
                        if (vibrator.hasAmplitudeControl()) { // Check if the device can control the vibration amplitude
                            // If yes, vibrate with specified pattern and amplitude through VibrationEffect
                            val effect = VibrationEffect.createWaveform(longArrayOf(0, 200), intArrayOf(0, 255), -1)
                            vibrator.vibrate(effect)
                            vibrationResult.value = "Vibration with amplitude control"
                        } else {
                            // If no amplitude control, vibrate normally without specifying amplitude
                            val effect = VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
                            vibrator.vibrate(effect)
                            vibrationResult.value = "Vibration without amplitude control"
                        }
                    } else {
                        // Log or show on UI that the device has no vibrator hardware
                        Log.e("Vibration Test", "This device does not support vibration")
                        vibrationResult.value = "This device does not support vibration"
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "Vibration Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:VibrationTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:VibrationTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:VibrationTest1", "nextPath: $nextPath")
                            Log.i("MyTag:VibrationTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = "aaaa"
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
                            }

                            navController.navigate(nextRouteWithArguments)
                        } else if (runningTestMode)
                            onTestComplete()
                        else
                            navController.popBackStack()
                    }
                }) {
                    Text(text = "Vibrate")
                }

                Text(text = vibrationResult.value)
            }
        }
    )
}