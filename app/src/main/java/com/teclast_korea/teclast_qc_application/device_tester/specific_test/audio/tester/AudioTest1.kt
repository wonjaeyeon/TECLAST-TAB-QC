package com.teclast_korea.teclast_qc_application.device_tester.specific_test.audio.tester

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.R
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.home.device_report.deviceSpecReportList
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
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
    val device_spec_pdf = deviceSpecReportList(context = context)


    // Define a cleanup function
//    fun cleanUpMediaPlayers() {
//        if (leftMediaPlayer.isPlaying) {
//            leftMediaPlayer.pause()
//        }
//        leftMediaPlayer.release()
//
//        if (rightMediaPlayer.isPlaying) {
//            rightMediaPlayer.pause()
//        }
//        rightMediaPlayer.release()
//    }

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
                    if (testMode == "NotTestMode"){
                        IconButton(onClick = {
                            cleanUpMediaPlayers()
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    } else{
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
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
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
                        cleanUpMediaPlayers()
                        onEvent(TestResultEvent.SaveTestResult)

                    }) {
                    Text("Fail")
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Press the buttons to play sounds on the left or right speaker.")
                Row() {
                    Button(
                        colors = ButtonDefaults.buttonColors(if (isLeftPlaying.value) Color.Green else Color.Gray),
                        onClick = {
                            isLeftPlaying.value = !leftMediaPlayer.isPlaying
                            if (leftMediaPlayer.isPlaying) {
                                leftMediaPlayer.pause()
                            } else {
                                leftMediaPlayer.start()
                            }
                        }
                    ) {
                        //volume up
                        Icon(
                            Icons.Filled.VolumeUp,
                            contentDescription = "Volume Up",
                            modifier = Modifier.graphicsLayer(scaleX = -1f)
                        ) // This line adds the icon and flips it horizontally

                        Text("Left", color = MaterialTheme.colors.onPrimary)
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(if (isRightPlaying.value) Color.Green else Color.Gray),
                        onClick = {
                            isRightPlaying.value = !rightMediaPlayer.isPlaying
                            if (rightMediaPlayer.isPlaying) {
                                rightMediaPlayer.pause()
                            } else {
                                rightMediaPlayer.start()
                            }
                        }
                    ) {

                        Text("Right", color = MaterialTheme.colors.onPrimary)
                        Icon(Icons.Filled.VolumeUp, contentDescription = "Volume Up") // This line adds the icon
                    }
                }
            }

        }
    }

    DisposableEffect(Unit) {
        onDispose {
            leftMediaPlayer.release()
            rightMediaPlayer.release()
        }
    }
}
