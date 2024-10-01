package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.lcd_screen.tester

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.domain.qc_results.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LcdTest2(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>(),
    isBottomBarVisible: MutableState<Boolean>
) {
    // 밝기 범위를 지정하고, 이를 흑백 색상으로 변환합니다.
    val brightnessLevels = listOf(0f, 0.25f, 0.5f, 0.75f, 1f)
    val colors = brightnessLevels.map { Color(1f, 1f, 1f, it) }

    var colorIndex by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val currentTestItem = "LCD Test 2"
    // val device_spec_pdf = deviceSpecReportList(context)

    val activity = context as? Activity
    val isNavigating = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isBottomBarVisible.value = false
        activity?.let {
            val windowInsetsController = WindowCompat.getInsetsController(it.window, it.window.decorView)
            // Hide both the system bars and navigation bars
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            // Add the following line if you want to prevent the system bars from appearing when the user swipes from the edge.
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    // Observe changes in the NavController back stack to revert immersive mode when navigating away
    DisposableEffect(isNavigating) {
        isBottomBarVisible.value = true
        onDispose {
            activity?.let {
                val windowInsetsController = WindowCompat.getInsetsController(it.window, it.window.decorView)
                // Show system bars when leaving the composable
                windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "LCD Screen Test T2") },
                backgroundColor = colors[colorIndex],
                contentColor = if (colorIndex >= 3) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    NavigationPopButton(
                        navController = navController,
                        testMode = testMode,
                        addtitionalAction = {
                            isNavigating.value = true
                            isBottomBarVisible.value = true
                        }
                    )
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
                        isNavigating.value = true
                        isBottomBarVisible.value = true
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "LCD Test 2",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest2
                            Log.i("MyTag:LCDTest2", "pastRoute: $pastRoute")
                            Log.i("MyTag:LCDTest2", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:LCDTest2", "nextPath: $nextPath")
                            Log.i("MyTag:LCDTest2", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = if (nextPathString.isNotEmpty()) {
                                "$nextRoute/$nextPathString/$testMode"
                            } else {
                                nextRoute
                            }

                            navController.navigate(nextRouteWithArguments)
                        }
                        else
                            navController.popBackStack()
                    }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                        isNavigating.value = true
                        isBottomBarVisible.value = true
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "LCD Test 2",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        FailTestNavigator(
                            onEvent = onEvent,
                            testMode = testMode,
                            navController = navController,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem
                        )

                    }) {
                    Text("Fail")
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors[colorIndex]),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = colorIndex.toString(),
                color = Color.Black,
                fontSize = 40.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        colorIndex = (colorIndex + 1) % colors.size
//                        if (colorIndex == colors.size - 1) {

//                            if (nextTestRoute.isNotEmpty()) {
//
//                                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest2
//                                Log.i("MyTag:LCDTEST2", "pastRoute: $pastRoute")
//                                Log.i("MyTag:LCDTEST2", "nextTestRoute: $nextTestRoute")
//                                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                                val nextPath = nextTestRoute.drop(1)
//                                val nextPathString = nextPath.joinToString(separator = "->")
//
//                                Log.i("MyTag", "nextPath: $nextPath")
//                                Log.i("MyTag", "nextPathString: $nextPathString")
//                                var nextRouteWithArguments = ""
//                                if (nextPathString.isNotEmpty()) {
//                                    nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
//                                } else {
//                                    nextRouteWithArguments = nextRoute
//                                }
//
//                                navController.navigate(nextRouteWithArguments)
//                            } else if (runningTestMode)
//                                
//                            else
//                                navController.popBackStack()
//                        }
                    }
            )
        }
    }
}
