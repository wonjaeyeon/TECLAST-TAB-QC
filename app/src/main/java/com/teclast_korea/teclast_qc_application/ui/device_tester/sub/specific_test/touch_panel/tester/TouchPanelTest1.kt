package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.domain.qc_results.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*


data class TouchPoint(val x: Float, val y: Float, val color: Color)


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelTest1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,

    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val touchPoints = remember { mutableStateListOf<TouchPoint>() }
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
    val touchCount = remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status

    val onTouchThresholdReached: () -> Unit = {
        touchCount.value++
        if (touchCount.value >= 10 && hasNavigated.value == false) {
            onEvent(TestResultEvent.SaveTestResult)
            AddTestResult(
                state = state,
                onEvent = onEvent,
                "Touch Panel Test 1",
                "Success",
                Date().toString()
            )
            onEvent(TestResultEvent.SaveTestResult)
            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                Log.i("MyTag:TouchPanelTest1", "pastRoute: $pastRoute")
                Log.i("MyTag:TouchPanelTest1", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "->")
                Log.i("MyTag:TouchPanelTest1", "nextPath: $nextPath")
                Log.i("MyTag:TouchPanelTest1", "nextPathString: $nextPathString")

                var nextRouteWithArguments: String
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
                } else {
                    nextRouteWithArguments = nextRoute
                }

                navController.navigate(nextRouteWithArguments)
            }
            else
                navController.popBackStack()
            hasNavigated.value = true
        } else {
            onEvent(TestResultEvent.SaveTestResult)
            AddTestResult(
                state = state,
                onEvent = onEvent,
                "Touch Panel Test 1",
                "Fail",
                Date().toString()
            )
            onEvent(TestResultEvent.SaveTestResult)


        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T1") },
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

        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = "Touch Count: ${touchCount.value}/10 times",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5
            )
            Canvas(modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val x = offset.x
                        val y = offset.y
                        val id = (touchPoints.size + 1) % colors.size

                        touchPoints.add(TouchPoint(x, y, colors[id]))
                        onTouchThresholdReached()
                    }

                    detectDragGestures { change, dragAmount ->
                        val x = change.position.x
                        val y = change.position.y
                        val id = change.id.value % colors.size

                        touchPoints.add(TouchPoint(x, y, colors[id.toInt()]))
                        onTouchThresholdReached()
                    }

                }) {

                touchPoints.forEach { touchPoint ->
                    drawCircle(color = touchPoint.color, center = Offset(touchPoint.x, touchPoint.y), radius = 20f)
                }
            }
        }
    }
}

