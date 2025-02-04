package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.domain.qc_result.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.DialogAPIInterface
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.TestAPIDialog
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*

interface OnMultiTouchListener {
    fun onMultiTouch()
}

class MultiTouchView(context: Context, private val listener: OnMultiTouchListener) : View(context) {
    private val pointerPositions = mutableMapOf<Int, PointF>()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val pointerIndex = event.actionIndex
                val pointerId = event.getPointerId(pointerIndex)
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)
                pointerPositions[pointerId] = PointF(x, y)
                if (pointerPositions.size == 5) {
                    listener.onMultiTouch()
                }
            }

            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val pointerId = event.getPointerId(i)
                    val x = event.getX(i)
                    val y = event.getY(i)
                    pointerPositions[pointerId] = PointF(x, y)
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                val pointerIndex = event.actionIndex
                val pointerId = event.getPointerId(pointerIndex)
                pointerPositions.remove(pointerId)
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((pointerId, position) in pointerPositions) {
            canvas.drawText("Pointer ID: ${pointerId + 1}", position.x, position.y, Paint().apply {
                color = Color.LightGray.toArgb()
                textSize = 50f
            })
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelTest4(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context, navController: NavController,
    testMode : String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val scaffoldState = rememberScaffoldState()
    //val pointerIds = remember { mutableStateListOf<Long>() }
    //val pointerPositions = remember { mutableStateMapOf<Long, Offset>() }
    val showDialog = remember { mutableStateOf(false) }

    // Initially set the test result to "Fail"
    LaunchedEffect(key1 = "initialTestResult") {
        onEvent(TestResultEvent.SaveTestResult)
        AddTestResult(
            state = state,
            onEvent = onEvent,
            itemName = "Touch Panel Test 4",
            testResult = "Fail",
            testDate = Date().toString()
        )
        onEvent(TestResultEvent.SaveTestResult)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T4") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                actions = {
                    DialogAPIInterface(
                        testMode = testMode,
                        showDialog = showDialog
                    )
                },
                navigationIcon = {
                    NavigationPopButton(navController = navController, testMode = testMode)
                }
            )
        }
    ) {
        TestAPIDialog(
            testMode = testMode,
            onEvent = onEvent,
            context = context,
            navController = navController,
            nextTestRoute = nextTestRoute,
            showDialog = showDialog
        )

        AndroidView(factory = { context ->
            MultiTouchView(context, object : OnMultiTouchListener {
                override fun onMultiTouch() {
                    // navController.popBackStack()
                    onEvent(TestResultEvent.SaveTestResult)
                    AddTestResult(
                        state = state,
                        onEvent = onEvent,
                        "Touch Panel Test 4",
                        "Success",
                        Date().toString()
                    )
                    onEvent(TestResultEvent.SaveTestResult)
                    if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                        val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                        Log.i("MyTag:TouchPanelTest4", "pastRoute: $pastRoute")
                        Log.i("MyTag:TouchPanelTest4", "nextTestRoute: $nextTestRoute")
                        val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                        val nextPath = nextTestRoute.drop(1)
                        val nextPathString = nextPath.joinToString(separator = "->")
                        Log.i("MyTag:TouchPanelTest4", "nextPath: $nextPath")
                        Log.i("MyTag:TouchPanelTest4", "nextPathString: $nextPathString")

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
                }
            })
        })
    }
}




