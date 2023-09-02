package com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LcdTest1(
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    testMode: String = "None",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.White, Color.Black)
    var colorIndex by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "LCD Screen Test T1") },
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
                .background(colors[colorIndex]),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$colorIndex",
                color = Color.Black,
                fontSize = 40.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        colorIndex = (colorIndex + 1) % colors.size
                        if (colorIndex == 0) {  // if we've looped back to the beginning
//
                            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                                Log.i("MyTag:LCDTEST1", "pastRoute: $pastRoute")
                                Log.i("MyTag:LCDTEST1", "nextTestRoute: $nextTestRoute")
                                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                                val nextPath = nextTestRoute.drop(1)
                                val nextPathString = nextPath.joinToString(separator = "->")
                                Log.i("MyTag:LCDTEST1", "nextPath: $nextPath")
                                Log.i("MyTag:LCDTEST1", "nextPathString: $nextPathString")

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
                    }
            )
        }
    }
}
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun lcdTest1(context: Context, navController: NavController) {
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.White, Color.Black)
//    var colorIndex by remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "LCD Screen Test T1") },
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
//
//        }
//    ) {
//        Box(modifier = Modifier
//            .fillMaxSize()
//            .background(colors[colorIndex])
//            .clickable {
//                colorIndex = (colorIndex + 1) % colors.size
//            })
//    }
//}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun lcdTest1(context: Context, navController: NavController) {
//    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.White, Color.Black)
//    var isGradient by remember { mutableStateOf(false) }
//    var colorIndex by remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "LCD Screen Test T1") },
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
//                .then(
//                    if (isGradient) {
//                        Modifier.background(
//                            Brush.verticalGradient(
//                                0f to Color.Red,
//                                0.33f to Color.Green,
//                                0.67f to Color.Blue,
//                                1f to Color.Black
//                            )
//                        )
//                    } else {
//                        Modifier.background(colors[colorIndex])
//                    }
//                )
//                .clickable {
//                    if (colorIndex < colors.size - 1) {
//                        colorIndex += 1
//                    } else {
//                        isGradient = !isGradient
//                        colorIndex = 0
//                    }
//                }
//        )
//    }
//}
