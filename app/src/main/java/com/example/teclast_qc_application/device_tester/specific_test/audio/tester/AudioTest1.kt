package com.example.teclast_qc_application.device_tester.specific_test.audio.tester

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
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
import com.example.teclast_qc_application.R
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResultV2
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioTestT1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
    leftMediaPlayer.setVolume(1f, 0f)
    val isLeftPlaying = remember { mutableStateOf(false) }

    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
    rightMediaPlayer.setVolume(0f, 1f)
    val isRightPlaying = remember { mutableStateOf(false) }

    // Define a cleanup function
    fun cleanUpMediaPlayers() {
        if (leftMediaPlayer.isPlaying) {
            leftMediaPlayer.pause()
        }
        leftMediaPlayer.release()

        if (rightMediaPlayer.isPlaying) {
            rightMediaPlayer.pause()
        }
        rightMediaPlayer.release()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Audio Test") },
                navigationIcon = {
                    IconButton(onClick = {
                        cleanUpMediaPlayers()
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                        AddTestResultV2(
                            state = state,
                            onEvent = onEvent,
                            "Audio Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)

                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:AudioTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:AudioTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:AudioTest1", "nextPath: $nextPath")
                            Log.i("MyTag:AudioTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = "aaaa"
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
                            }
                            cleanUpMediaPlayers()
                            navController.navigate(nextRouteWithArguments)
                        } else if (runningTestMode) {
                            cleanUpMediaPlayers()
                            onTestComplete()
                        } else {
                            cleanUpMediaPlayers()
                            navController.popBackStack()
                        }
                    }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResultV2(
                            state = state,
                            onEvent = onEvent,
                            "Audio Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:AudioTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:AudioTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:AudioTest1", "nextPath: $nextPath")
                            Log.i("MyTag:AudioTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = "aaaa"
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
                            }
                            cleanUpMediaPlayers()
                            onEvent(TestResultEvent.SaveTestResult)
                            navController.navigate(nextRouteWithArguments)
                        } else if (runningTestMode) {
                            cleanUpMediaPlayers()
                            onEvent(TestResultEvent.SaveTestResult)
                            onTestComplete()
                        } else {
                            cleanUpMediaPlayers()
                            onEvent(TestResultEvent.SaveTestResult)
                            navController.popBackStack()
                        }
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

                        Text("Left", color = Color.White)
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

                        Text("Right", color = Color.White)
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
