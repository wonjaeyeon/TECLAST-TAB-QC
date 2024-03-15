package com.teclast_korea.teclast_qc_application.device_tester.specific_test.g_sensor.tester

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.home.device_report.DeviceSpecReportList
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*


class GSensorView(context: Context) : View(context), SensorEventListener {
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    var sensorValues: List<Float> = listOf(0f, 0f, 0f)

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        sensorValues = event.values.toList()
        postInvalidate()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // handle if needed
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        sensorManager.unregisterListener(this)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GSensorTestT1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val sensorValues = remember { mutableStateOf(listOf(0f, 0f, 0f)) }
    val currentTestItem = "G-Sensor Test 1"
    val device_spec_pdf = DeviceSpecReportList(context)

    val sensorListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                sensorValues.value = event.values.toList()
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // handle if needed
            }
        }
    }

    DisposableEffect(sensorListener) {
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Accelerometer Test") },
                navigationIcon = {
                    NavigationPopButton(
                        navController = navController, testMode = testMode
                    )
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally, // Center content along the cross-axis
            ) {
                Text(
                    text = "Accelerometer Test",
                    style = MaterialTheme.typography.h4 // Use a larger text style, adjust as needed
                )
                Column(modifier = Modifier.padding(horizontal = 8.dp), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "X: ${String.format("%.2f", sensorValues.value[0])}",
                        style = MaterialTheme.typography.h5 // Use a larger text style, adjust as needed
                    )
                    Text(
                        text = "Y: ${String.format("%.2f", sensorValues.value[1])}",
                        style = MaterialTheme.typography.h5 // Use a larger text style, adjust as needed
                    )
                    Text(
                        text = "Z: ${String.format("%.2f", sensorValues.value[2])}",
                        style = MaterialTheme.typography.h5 // Use a larger text style, adjust as needed
                    )
                }

            }
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
                            "G-Sensor Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:G-SensorTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:G-SensorTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:G-SensorTest1", "nextPath: $nextPath")
                            Log.i("MyTag:G-SensorTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = ""
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
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
                            "G-Sensor Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        FailTestNavigator(
                            context = context,
                            onEvent = onEvent,
                            state = state,
                            navController = navController,
                            testMode = testMode,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem,
                            deviceSpec = device_spec_pdf
                        )
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:G-SensorTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:G-SensorTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:G-SensorTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:G-SensorTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = "${nextTestRoute[0]}"
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
        }
    )
}



