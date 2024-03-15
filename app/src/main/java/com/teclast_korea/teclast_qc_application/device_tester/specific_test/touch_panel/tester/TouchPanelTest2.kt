package com.teclast_korea.teclast_qc_application.device_tester.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.DialogAPIInterface
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.TestAPIDialog
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelTest2(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val trail = remember { mutableStateListOf<Offset>() }
    val scaffoldState = rememberScaffoldState()
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(Size.Zero) }
    var isFinished by remember { mutableStateOf(false) }
    val hasAddedResult = remember { mutableStateOf(false) }  // State to track navigation status
    val showDialog = remember { mutableStateOf(false) }

    // Initialize checkpoints
    val checkpoints = remember {
        mutableStateMapOf(
            "top" to false,
            "bottom" to false,
            "left" to false,
            "right" to false,
            "diag1" to false,
            "diag2" to false,
            "pointLT" to false,
            "pointLB" to false,
            "pointRT" to false,
            "pointRB" to false,
            "pointCT" to false,
            "pointCB" to false,
            "pointLC" to false,
            "pointRC" to false,
            "pointCC" to false,
        )
    }


    // Initially set the test result to "Fail"
    LaunchedEffect(key1 = "initialTestResult") {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(
            state = state,
            onEvent = onEvent,
            itemName = "Touch Panel Test 2",
            testResult = "Fail",
            testDate = Date().toString()
        )
        onEvent(TestResultEvent.SaveTestResult)
    }

    val onTouchThresholdReached: () -> Unit = {
        // Check if all checkpoints are true
        if (checkpoints.values.all { it } && !isFinished) {
            // All checkpoints are true, so we can navigate to the next screen
            // navController.popBackStack()
            onEvent(TestResultEvent.SaveTestResult)
            AddTestResult(
                state = state,
                onEvent = onEvent,
                "Touch Panel Test 2",
                "Success",
                Date().toString()
            )
            onEvent(TestResultEvent.SaveTestResult)
            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                Log.i("MyTag:TouchPanelTest2", "pastRoute: $pastRoute")
                Log.i("MyTag:TouchPanelTest2", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "->")
                Log.i("MyTag:TouchPanelTest2", "nextPath: $nextPath")
                Log.i("MyTag:TouchPanelTest2", "nextPathString: $nextPathString")

                var nextRouteWithArguments = ""
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
                } else {
                    nextRouteWithArguments = "${nextTestRoute[0]}"
                }

                navController.navigate(nextRouteWithArguments)
            } else
                navController.popBackStack()
            isFinished = true

        } else {
            if (!hasAddedResult.value) {
                onEvent(TestResultEvent.SaveTestResult)
                AddTestResult(
                    state = state,
                    onEvent = onEvent,
                    "Touch Panel Test 2",
                    "Fail",
                    Date().toString()
                )
                onEvent(TestResultEvent.SaveTestResult)
                hasAddedResult.value = true
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T2") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
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
                }
            )
        }
    ) {
        TestAPIDialog(
            testMode = testMode,
            state = state,
            onEvent = onEvent,
            context = context,
            navController = navController,
            nextTestRoute = nextTestRoute,
            showDialog = showDialog
        )


        Box(
            Modifier.fillMaxSize()
                .onSizeChanged { size = it.toSize() }
        ) {
            // Draw the track
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(color = Color.Gray, style = Stroke(width = 22f))
                drawLine(
                    Color.Gray,
                    start = Offset.Zero,
                    end = Offset(size.width, size.height),
                    strokeWidth = 12f
                )
                drawLine(
                    Color.Gray,
                    start = Offset(size.width, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 12f
                )

                // Draw the trail spots
                for (spot in trail) {
                    drawCircle(
                        color = Color.Red,
                        center = spot,
                        radius = 50f // Same as the size of the blue circle
                    )
                }
            }

            // Draw the draggable circle
            Box(
                Modifier.offset {
                    IntOffset(
                        (offsetX.value - 25f).roundToInt(),
                        (offsetY.value - 25f).roundToInt()
                    )
                } // Adjust the offset
                    .size(50.dp)
                    .background(Color.Blue, shape = CircleShape)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            val original = Offset(offsetX.value, offsetY.value)
                            val summed = original + dragAmount
                            val newValue = Offset(
                                x = summed.x.coerceIn(25f, size.width - 25.dp.toPx()), // Adjust the boundaries
                                y = summed.y.coerceIn(25f, size.height - 25.dp.toPx()) // Adjust the boundaries
                            )
                            change.consume()
                            offsetX.value = newValue.x
                            offsetY.value = newValue.y

                            trail.add(newValue)

                            // Check if the touch point crossed any checkpoint
                            if (newValue.y < 50f) checkpoints["top"] = true
                            if (newValue.y > size.height - 50.dp.toPx()) checkpoints["bottom"] = true
                            if (newValue.x < 50f) checkpoints["left"] = true
                            if (newValue.x > size.width - 50.dp.toPx()) checkpoints["right"] = true
                            if (abs(newValue.x - newValue.y) < 50f) checkpoints["diag1"] = true
                            if (abs(newValue.x + newValue.y - size.width) < 50f) checkpoints["diag2"] = true

                            if (newValue.x < 50f && newValue.y < 50f) checkpoints["pointLT"] = true
                            if (newValue.x < 50f && newValue.y > size.height - 50.dp.toPx()) checkpoints["pointLB"] =
                                true
                            if (newValue.x > size.width - 50.dp.toPx() && newValue.y < 50f) checkpoints["pointRT"] =
                                true
                            if (newValue.x > size.width - 50.dp.toPx() && newValue.y > size.height - 50.dp.toPx()) checkpoints["pointRB"] =
                                true
                            if (newValue.x > size.width / 2 - 50.dp.toPx() && newValue.x < size.width / 2 + 50.dp.toPx() && newValue.y < 50f) checkpoints["pointCT"] =
                                true
                            if (newValue.x > size.width / 2 - 50.dp.toPx() && newValue.x < size.width / 2 + 50.dp.toPx() && newValue.y > size.height - 50.dp.toPx()) checkpoints["pointCB"] =
                                true
                            if (newValue.x < 50f && newValue.y > size.height / 2 - 50.dp.toPx() && newValue.y < size.height / 2 + 50.dp.toPx()) checkpoints["pointLC"] =
                                true
                            if (newValue.x > size.width - 50.dp.toPx() && newValue.y > size.height / 2 - 50.dp.toPx() && newValue.y < size.height / 2 + 50.dp.toPx()) checkpoints["pointRC"] =
                                true
                            if (newValue.x > size.width / 2 - 50.dp.toPx() && newValue.x < size.width / 2 + 50.dp.toPx() && newValue.y > size.height / 2 - 50.dp.toPx() && newValue.y < size.height / 2 + 50.dp.toPx()) checkpoints["pointCC"] =
                                true


                            onTouchThresholdReached()
                        }
                    }
            )
        }
    }
}
