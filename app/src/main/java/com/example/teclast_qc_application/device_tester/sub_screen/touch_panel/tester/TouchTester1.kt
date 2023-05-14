package com.example.teclast_qc_application.device_tester.sub_screen.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.teclast_qc_application.test_result.addTestResult


data class TouchPoint(val x: Float, val y: Float, val color: Color)


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelT1(context: Context, navController: NavController) {
    val touchPoints = remember { mutableStateListOf<TouchPoint>() }
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
    val touchCount = remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val testResult = remember { mutableStateOf("") }

    val onTouchThresholdReached: () -> Unit = {
        touchCount.value++
        if (touchCount.value >= 10) {
            //erase touch_panel_test_t1_screen form navicontroller
            navController.popBackStack()
            //navController.navigate("touch_panel_test_screen")
            testResult.value = "Touch Test T1: Pass"
            addTestResult("Touch Test T1", "Pass")
        }
        else {
            testResult.value = "Touch Test T1: Fail"
            addTestResult("Touch Test T1", "Fail")

        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T1") },
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(text = "Touch Count: ${touchCount.value}/10 times", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, color = Color.White, style = MaterialTheme.typography.h5)
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
    }}

