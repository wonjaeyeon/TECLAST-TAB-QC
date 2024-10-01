package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.vibration.tester

import android.annotation.SuppressLint
import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.TestResultState
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun VibrationTestT1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context, navController: NavController
) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    var vibrationResult = remember { mutableStateOf("Ready for Test") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Vibration Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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
                    }
                }) {
                    Text(text = "Vibrate")
                }

                Text(text = vibrationResult.value)
            }
        }
    )
}
