package com.example.teclast_qc_application.device_tester.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import kotlin.math.abs
import kotlin.math.roundToInt

//
//data class TouchPoint(val x: Float, val y: Float, val color: Color)


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun touchPanelT2(context: Context, navController: NavController) {
//    val pointerPositions = remember { mutableMapOf<PointerId, Offset>() }
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
//    val testResult = remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T2") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = Color.White,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.primaryVariant)
//        ) {
//            Canvas(modifier = Modifier
//                .fillMaxSize()
//                .pointerInput(Unit) {
//                    detectDragGestures { change, dragAmount ->
//                        pointerPositions[change.id] = change.position
//                        change.consume()
//                    }
//                }) {
//                pointerPositions.forEach { (id, offset) ->
//                    val color = colors[(id.value % colors.size).toInt()]
//                    drawCircle(color = color, center = offset, radius = 20f)
//                }
//            }
//        }
//    }
//}



//

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun touchPanelT2(context: Context, navController: NavController) {
//    val touchPoints = remember { mutableStateListOf<TouchPoint>() }
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
//    val touchCount = remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    val offsetX = remember { mutableStateOf(0f) }
//    val offsetY = remember { mutableStateOf(0f) }
//    var size by remember { mutableStateOf(Size.Zero) }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T2") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = Color.White,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            Modifier.fillMaxSize()
//                .onSizeChanged { size = it.toSize() }
//        ) {
//            // Draw the track
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                drawRect(color = Color.Gray, style = Stroke(width = 22f))
//                drawLine(
//                    Color.Gray,
//                    start = Offset.Zero,
//                    end = Offset(size.width, size.height),
//                    strokeWidth = 12f
//                )
//                drawLine(
//                    Color.Gray,
//                    start = Offset(size.width, 0f),
//                    end = Offset(0f, size.height),
//                    strokeWidth = 12f
//                )
//            }
//
//            // Draw the draggable box
//            Box(
//                Modifier.offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
//                    .size(50.dp)
//                    .background(Color.Blue)
//                    .pointerInput(Unit) {
//                        detectDragGestures { change, dragAmount ->
//                            val original = Offset(offsetX.value, offsetY.value)
//                            val summed = original + dragAmount
//                            val newValue = Offset(
//                                x = summed.x.coerceIn(0f, size.width - 50.dp.toPx()),
//                                y = summed.y.coerceIn(0f, size.height - 50.dp.toPx())
//                            )
//                            change.consume()
//                            offsetX.value = newValue.x
//                            offsetY.value = newValue.y
//                        }
//                    }
//            )
//        }
//    }
//}


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun touchPanelT2(context: Context, navController: NavController) {
//    val trail = remember { mutableStateListOf<Offset>() }
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
//    val touchCount = remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    val offsetX = remember { mutableStateOf(0f) }
//    val offsetY = remember { mutableStateOf(0f) }
//    var size by remember { mutableStateOf(Size.Zero) }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T2") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = Color.White,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            Modifier.fillMaxSize()
//                .onSizeChanged { size = it.toSize() }
//        ) {
//            // Draw the track
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                drawRect(color = Color.Gray, style = Stroke(width = 22f))
//                drawLine(
//                    Color.Gray,
//                    start = Offset.Zero,
//                    end = Offset(size.width, size.height),
//                    strokeWidth = 12f
//                )
//                drawLine(
//                    Color.Gray,
//                    start = Offset(size.width, 0f),
//                    end = Offset(0f, size.height),
//                    strokeWidth = 12f
//                )
//
//                // Draw the trail
//                for (i in 1 until trail.size) {
//                    val start = trail[i - 1]
//                    val end = trail[i]
//                    drawLine(
//                        color = Color.Red,
//                        start = start,
//                        end = end,
//                        strokeWidth = 40f
//                    )
//                }
//            }
//
//            // Draw the draggable box
//            Box(
//                Modifier.offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
//                    .size(50.dp)
//                    .background(Color.Blue)
//                    .pointerInput(Unit) {
//                        detectDragGestures { change, dragAmount ->
//                            val original = Offset(offsetX.value, offsetY.value)
//                            val summed = original + dragAmount
//                            val newValue = Offset(
//                                x = summed.x.coerceIn(0f, size.width - 50.dp.toPx()),
//                                y = summed.y.coerceIn(0f, size.height - 50.dp.toPx())
//                            )
//                            change.consume()
//                            offsetX.value = newValue.x
//                            offsetY.value = newValue.y
//
//                            trail.add(newValue)
//                        }
//                    }
//            )
//        }
//    }
//}


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun touchPanelT2(context: Context, navController: NavController) {
//    val trail = remember { mutableStateListOf<Offset>() }
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
//    val touchCount = remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    val offsetX = remember { mutableStateOf(0f) }
//    val offsetY = remember { mutableStateOf(0f) }
//    var size by remember { mutableStateOf(Size.Zero) }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T2") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = Color.White,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            Modifier.fillMaxSize()
//                .onSizeChanged { size = it.toSize() }
//        ) {
//            // Draw the track
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                drawRect(color = Color.Gray, style = Stroke(width = 22f))
//                drawLine(
//                    Color.Gray,
//                    start = Offset.Zero,
//                    end = Offset(size.width, size.height),
//                    strokeWidth = 12f
//                )
//                drawLine(
//                    Color.Gray,
//                    start = Offset(size.width, 0f),
//                    end = Offset(0f, size.height),
//                    strokeWidth = 12f
//                )
//
//                // Draw the trail spots
//                for (spot in trail) {
//                    drawCircle(
//                        color = Color.Red,
//                        center = spot,
//                        radius = 50f // Adjust as needed
//                    )
//                }
//            }
//
//            // Draw the draggable circle
//            Box(
//                Modifier.offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
//                    .size(50.dp)
//                    .background(Color.Blue, shape = CircleShape)
//                    .pointerInput(Unit) {
//                        detectDragGestures { change, dragAmount ->
//                            val original = Offset(offsetX.value, offsetY.value)
//                            val summed = original + dragAmount
//                            val newValue = Offset(
//                                x = summed.x.coerceIn(0f, size.width - 50.dp.toPx()),
//                                y = summed.y.coerceIn(0f, size.height - 50.dp.toPx())
//                            )
//                            change.consume()
//                            offsetX.value = newValue.x
//                            offsetY.value = newValue.y
//
//                            trail.add(newValue)
//                        }
//                    }
//            )
//        }
//    }
//}


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun touchPanelT2(context: Context, navController: NavController) {
//    val trail = remember { mutableStateListOf<Offset>() }
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)
//    val touchCount = remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    val offsetX = remember { mutableStateOf(0f) }
//    val offsetY = remember { mutableStateOf(0f) }
//    var size by remember { mutableStateOf(Size.Zero) }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T2") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = Color.White,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            Modifier.fillMaxSize()
//                .onSizeChanged { size = it.toSize() }
//        ) {
//            // Draw the track
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                drawRect(color = Color.Gray, style = Stroke(width = 22f))
//                drawLine(
//                    Color.Gray,
//                    start = Offset.Zero,
//                    end = Offset(size.width, size.height),
//                    strokeWidth = 12f
//                )
//                drawLine(
//                    Color.Gray,
//                    start = Offset(size.width, 0f),
//                    end = Offset(0f, size.height),
//                    strokeWidth = 12f
//                )
//
//                // Draw the trail spots
//                for (spot in trail) {
//                    drawCircle(
//                        color = Color.Red,
//                        center = spot,
//                        radius = 50f // Same as the size of the blue circle
//                    )
//                }
//            }
//
//            // Draw the draggable circle
//            Box(
//                Modifier.offset { IntOffset((offsetX.value - 25f).roundToInt(), (offsetY.value - 25f).roundToInt()) } // Adjust the offset
//                    .size(50.dp)
//                    .background(Color.Blue, shape = CircleShape)
//                    .pointerInput(Unit) {
//                        detectDragGestures { change, dragAmount ->
//                            val original = Offset(offsetX.value, offsetY.value)
//                            val summed = original + dragAmount
//                            val newValue = Offset(
//                                x = summed.x.coerceIn(25f, size.width - 25.dp.toPx()), // Adjust the boundaries
//                                y = summed.y.coerceIn(25f, size.height - 25.dp.toPx()) // Adjust the boundaries
//                            )
//                            change.consume()
//                            offsetX.value = newValue.x
//                            offsetY.value = newValue.y
//
//                            trail.add(newValue)
//                        }
//                    }
//            )
//        }
//    }
//}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelT2(context: Context, navController: NavController) {
    val trail = remember { mutableStateListOf<Offset>() }
    val touchCount = remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(Size.Zero) }
    var isFinished by remember { mutableStateOf(false) }

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

    val onTouchThresholdReached: () -> Unit = {
        // Check if all checkpoints are true
        if (checkpoints.values.all { it } && !isFinished) {
            // All checkpoints are true, so we can navigate to the next screen
            navController.popBackStack()
            isFinished = true

        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T2") },
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
                Modifier.offset { IntOffset((offsetX.value - 25f).roundToInt(), (offsetY.value - 25f).roundToInt()) } // Adjust the offset
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
                            if (newValue.x < 50f && newValue.y > size.height - 50.dp.toPx()) checkpoints["pointLB"] = true
                            if (newValue.x > size.width - 50.dp.toPx() && newValue.y < 50f) checkpoints["pointRT"] = true
                            if (newValue.x > size.width - 50.dp.toPx() && newValue.y > size.height - 50.dp.toPx()) checkpoints["pointRB"] = true
                            if (newValue.x > size.width / 2 - 50.dp.toPx() && newValue.x < size.width / 2 + 50.dp.toPx() && newValue.y < 50f) checkpoints["pointCT"] = true
                            if (newValue.x > size.width / 2 - 50.dp.toPx() && newValue.x < size.width / 2 + 50.dp.toPx() && newValue.y > size.height - 50.dp.toPx()) checkpoints["pointCB"] = true
                            if (newValue.x < 50f && newValue.y > size.height / 2 - 50.dp.toPx() && newValue.y < size.height / 2 + 50.dp.toPx()) checkpoints["pointLC"] = true
                            if (newValue.x > size.width - 50.dp.toPx() && newValue.y > size.height / 2 - 50.dp.toPx() && newValue.y < size.height / 2 + 50.dp.toPx()) checkpoints["pointRC"] = true
                            if (newValue.x > size.width / 2 - 50.dp.toPx() && newValue.x < size.width / 2 + 50.dp.toPx() && newValue.y > size.height / 2 - 50.dp.toPx() && newValue.y < size.height / 2 + 50.dp.toPx()) checkpoints["pointCC"] = true


//                            // Check if all checkpoints have been crossed
//                            if (checkpoints.all { it.value }) {
//                                navController.popBackStack()
//                            }
                            onTouchThresholdReached()
                        }
                    }
            )
        }
    }
}



fun addTestResult(testName: String, testResult: String) {
    // add your logic to save the test results
}
