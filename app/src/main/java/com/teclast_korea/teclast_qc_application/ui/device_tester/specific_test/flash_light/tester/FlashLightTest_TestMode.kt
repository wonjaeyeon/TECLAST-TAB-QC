package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.flash_light.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOff
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultState
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FlashLightTestTestMode(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val isFlashOn = remember { mutableStateOf(false) }
    val flashlightResult = remember { mutableStateOf("Ready for Test") }
    val currentTestItem = "Flashlight Test 1"
    // val device_spec_pdf = deviceSpecReportList(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flashlight Test") },
                navigationIcon = {
                    NavigationPopButton(navController = navController, testMode = testMode)
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
                            "Flashlight Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:FlashLightTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:FlashLightTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:FlashLightTest1", "nextPath: $nextPath")
                            Log.i("MyTag:FlashLightTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = if (nextPathString.isNotEmpty()) {
                                "$nextRoute/$nextPathString/$testMode"
                            } else {
                                nextRoute
                            }

                            navController.navigate(nextRouteWithArguments)
                        }
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
                            "Flashlight Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        FailTestNavigator(
                            onEvent = onEvent,
                            testMode = testMode,
                            navController = navController,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem
                        )
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:FlashLightTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:FlashLightTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:FlashLightTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:FlashLightTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = nextRoute
//                            }
//
//                            navController.navigate(nextRouteWithArguments)
//                        }
//                        else
//                            navController.popBackStack()
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
                Icon(
                    imageVector = if (isFlashOn.value) Icons.Filled.FlashlightOn else Icons.Filled.FlashlightOff,
                    contentDescription = if (isFlashOn.value) "Flashlight is on" else "Flashlight is off",
                    modifier = Modifier.size(40.dp),
                    tint = if (isFlashOn.value) Color.Yellow.copy(alpha = 0.6f) else MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(16.dp)) // Space between icon and button
                Button(onClick = {
                    isFlashOn.value = !isFlashOn.value
                    flashlightResult.value = toggleFlashLight(context, isFlashOn.value)
                    if (flashlightResult.value.startsWith("Cannot find Flashlight")) {
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "Flashlight Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        FailTestNavigator(
                            onEvent = onEvent,
                            testMode = testMode,
                            navController = navController,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem
                        )
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:FlashLightTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:FlashLightTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:FlashLightTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:FlashLightTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = nextRoute
//                            }
//
//                            navController.navigate(nextRouteWithArguments)
//                        }
//                        else
//                            navController.popBackStack()
                    }

                }) {
                    Text(text = if (isFlashOn.value) "Turn off Flashlight" else "Turn on Flashlight")
                }

                Text(text = flashlightResult.value)
            }
        }
    )
}