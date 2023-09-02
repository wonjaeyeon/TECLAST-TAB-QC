package com.example.teclast_qc_application.device_tester.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController


//class MultiTouchView(context: Context) : View(context) {
//    private val pointerPositions = mutableMapOf<Int, PointF>()
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        when (event.actionMasked) {
//            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
//                val pointerIndex = event.actionIndex
//                val pointerId = event.getPointerId(pointerIndex)
//                val x = event.getX(pointerIndex)
//                val y = event.getY(pointerIndex)
//                pointerPositions[pointerId] = PointF(x, y)
//            }
//            MotionEvent.ACTION_MOVE -> {
//                for (i in 0 until event.pointerCount) {
//                    val pointerId = event.getPointerId(i)
//                    val x = event.getX(i)
//                    val y = event.getY(i)
//                    pointerPositions[pointerId] = PointF(x, y)
//                }
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
//                val pointerIndex = event.actionIndex
//                val pointerId = event.getPointerId(pointerIndex)
//                pointerPositions.remove(pointerId)
//            }
//        }
//        invalidate()
//        return true
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        for ((pointerId, position) in pointerPositions) {
//            // Draw something for each touch point.
//            // For example, draw the pointer id:
//            canvas.drawText("Pointer ID: ${pointerId+1}", position.x, position.y, Paint().apply {
//                color = Color.LightGray.toArgb()
//                textSize = 50f
//            })
//        }
//    }
//}
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
            canvas.drawText("Pointer ID: ${pointerId+1}", position.x, position.y, Paint().apply {
                color = Color.LightGray.toArgb()
                textSize = 50f
            })
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelTest4(context: Context, navController: NavController,
                    runningTestMode: Boolean = false,
                    onTestComplete: () -> Unit = {},
                    navigateToNextTest: Boolean = false,
                    nextTestRoute: MutableList<String> = mutableListOf<String>()) {
    val scaffoldState = rememberScaffoldState()
    val pointerIds = remember { mutableStateListOf<Long>() }
    val pointerPositions = remember { mutableStateMapOf<Long, Offset>() }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T4") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
//        val pointerIds = remember { mutableStateListOf<Long>() }
//        val pointerPositions = remember { mutableStateMapOf<Long, Offset>() }
//
//        AndroidView(factory = { context -> MultiTouchView(context) })
        AndroidView(factory = { context -> MultiTouchView(context, object : OnMultiTouchListener {
            override fun onMultiTouch() {
                // navController.popBackStack()yfgth
                if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                    val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                    Log.i("MyTag:TOUCHPANELTEST4", "pastRoute: $pastRoute")
                    Log.i("MyTag:TOUCHPANELTEST4", "nextTestRoute: $nextTestRoute")
                    val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                    val nextPath = nextTestRoute.drop(1)
                    val nextPathString = nextPath.joinToString(separator = "->")
                    Log.i("MyTag:TOUCHPANELTEST4", "nextPath: $nextPath")
                    Log.i("MyTag:TOUCHPANELTEST4", "nextPathString: $nextPathString")

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
        })})
}}




