package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.audio.tester

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.R
import com.teclast_korea.teclast_qc_application.domain.qc_results.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.audio.tester.tablet_ui.TabletUI
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioTestT1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
    leftMediaPlayer.setVolume(1f, 0f)
    val isLeftPlaying = remember { mutableStateOf(false) }

    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
    rightMediaPlayer.setVolume(0f, 1f)
    val isRightPlaying = remember { mutableStateOf(false) }

    val leftMediaPlayerReleased = remember { mutableStateOf(false) }
    val rightMediaPlayerReleased = remember { mutableStateOf(false) }

    val currentTestItem = "Audio Test 1"

    var size by remember { mutableStateOf(Size.Zero) }

    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val sensorValues = remember { mutableStateOf(listOf(0f, 0f, 0f)) }
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

    val model = Build.MODEL

    DisposableEffect(sensorListener) {
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    fun cleanUpMediaPlayers() {
        if (!leftMediaPlayerReleased.value && leftMediaPlayer.isPlaying) {
            leftMediaPlayer.pause()
        }
        if (!leftMediaPlayerReleased.value) {
            leftMediaPlayer.release()
            leftMediaPlayerReleased.value = true
        }

        if (!rightMediaPlayerReleased.value && rightMediaPlayer.isPlaying) {
            rightMediaPlayer.pause()
        }
        if (!rightMediaPlayerReleased.value) {
            rightMediaPlayer.release()
            rightMediaPlayerReleased.value = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Audio Test 1") },
                navigationIcon = {
                    if (testMode == "NotTestMode") {
                        IconButton(onClick = {
                            cleanUpMediaPlayers()
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    } else {
                        NavigationPopButton(
                            navController = navController, testMode = testMode
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
                        cleanUpMediaPlayers()

                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "Audio Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)

                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            //Log.i("MyTag:AudioTest1", "pastRoute: $pastRoute")
                            //Log.i("MyTag:AudioTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            //Log.i("MyTag:AudioTest1", "nextPath: $nextPath")
                            //Log.i("MyTag:AudioTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = if (nextPathString.isNotEmpty()) {
                                "$nextRoute/$nextPathString/$testMode"
                            } else {
                                nextRoute
                            }
                            cleanUpMediaPlayers()
                            onEvent(TestResultEvent.SaveTestResult)
                            navController.navigate(nextRouteWithArguments)
                        } else {
                            cleanUpMediaPlayers()
                            onEvent(TestResultEvent.SaveTestResult)
                            navController.popBackStack()
                        }
                    }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                        cleanUpMediaPlayers()

                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "Audio Test 1",
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
                        cleanUpMediaPlayers()
                        onEvent(TestResultEvent.SaveTestResult)

                    }) {
                    Text("Fail")
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .onSizeChanged { newSize ->
                    size = newSize.toSize()
                },
            contentAlignment = Alignment.Center
        ) {
            TabletUI(
                leftMediaPlayer = leftMediaPlayer,
                rightMediaPlayer = rightMediaPlayer,
                isLeftPlaying = isLeftPlaying,
                isRightPlaying = isRightPlaying,
                sensorValues = sensorValues,
                size = size,
                model = model
            )

        }
    }

    DisposableEffect(Unit) {
        onDispose {
            leftMediaPlayer.release()
            rightMediaPlayer.release()
        }
    }
}
