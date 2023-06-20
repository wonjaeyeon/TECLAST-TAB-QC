package com.example.teclast_qc_application.device_tester.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun TouchPanelT3(context: Context, navController: NavController) {
//    val scaffoldState = rememberScaffoldState()
//    // set up all transformation states
//    var scale by remember { mutableStateOf(1f) }
//    var rotation by remember { mutableStateOf(0f) }
//    var offset by remember { mutableStateOf(Offset.Zero) }
//    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
//        scale *= zoomChange
//        rotation += rotationChange
//        offset += offsetChange
//    }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T3") },
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
//        //Background Box
//        Box(
//            Modifier.fillMaxWidth().fillMaxHeight()
//                .background(MaterialTheme.colors.primaryVariant)
//        ){
//            // transformable box
//        Box(
//            Modifier
//                // apply other transformations like rotation and zoom
//                // on the pizza slice emoji
//                .graphicsLayer(
//                    scaleX = scale,
//                    scaleY = scale,
//                    rotationZ = rotation,
//                    translationX = offset.x,
//                    translationY = offset.y
//                )
//                // add transformable to listen to multitouch transformation events
//                // after offset
//                .transformable(state = state)
//                .background(Color.Green)
//                .size(100.dp)
//        )}
//    }
//}


//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun TouchPanelT3(context: Context, navController: NavController) {
//    val scaffoldState = rememberScaffoldState()
//    // set up all transformation states
//    var scale by remember { mutableStateOf(1f) }
//    var rotation by remember { mutableStateOf(0f) }
//    var offset by remember { mutableStateOf(Offset.Zero) }
//    var containerSize by remember { mutableStateOf(IntSize.Zero) }
//    var boxSize by remember { mutableStateOf(IntSize.Zero) }
//
//    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
//        scale *= zoomChange
//        rotation += rotationChange
//
//        // Calculate new offset
//        val newOffset = offset + offsetChange
//        // Calculate boundaries
//        val maxX = containerSize.width / 2f - boxSize.width / 2f * scale
//        val maxY = containerSize.height / 2f - boxSize.height / 2f * scale
//
//        // Apply boundaries
//        offset = Offset(
//            newOffset.x.coerceIn(-maxX, maxX),
//            newOffset.y.coerceIn(-maxY, maxY)
//        )
//    }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Touch Test T3") },
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
//        // Background Box
//        ConstraintLayout(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.primaryVariant)
//                .onSizeChanged {
//                    containerSize = it
//                }
//        ) {
//            val box = createRef()
//            // transformable box
//            Box(
//                Modifier
//                    .constrainAs(box) {
//                        centerTo(parent)
//                    }
//                    // apply other transformations like rotation and zoom
//                    .graphicsLayer(
//                        scaleX = scale,
//                        scaleY = scale,
//                        rotationZ = rotation,
//                        translationX = offset.x,
//                        translationY = offset.y
//                    )
//                    // add transformable to listen to multitouch transformation events
//                    .transformable(state = state)
//                    .background(Color.Green)
//                    .size(100.dp)
//                    .onSizeChanged {
//                        boxSize = it
//                    }
//            )
//        }
//    }
//}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelT3(context: Context, navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var boxSize by remember { mutableStateOf(IntSize.Zero) }

    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        // Limit scale to a certain range
        scale = (scale * zoomChange).coerceIn(0.1f, 3.5f)
        rotation += rotationChange

        // Calculate new offset
        val newOffset = offset + offsetChange
        // Calculate boundaries
        val maxX = (containerSize.width / 2f - boxSize.width / 2f * scale).coerceAtLeast(0f)
        val maxY = (containerSize.height / 2f - boxSize.height / 2f * scale).coerceAtLeast(0f)

        // Apply boundaries
        offset = Offset(
            newOffset.x.coerceIn(-maxX, maxX),
            newOffset.y.coerceIn(-maxY, maxY)
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T3") },
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
        // Background Box
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
                .onSizeChanged {
                    containerSize = it
                }
        ) {
            val box = createRef()
            // transformable box
            Box(
                Modifier
                    .constrainAs(box) {
                        centerTo(parent)
                    }
                    // apply other transformations like rotation and zoom
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    // add transformable to listen to multitouch transformation events
                    .transformable(state = state)
                    .background(Color.Green)
                    .size(100.dp)
                    .onSizeChanged {
                        boxSize = it
                    }
            )
        }
    }
}


