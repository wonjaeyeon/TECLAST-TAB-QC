package com.teclast_korea.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.device_tester.standard_test.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.home.device_report.deviceSpecReportList
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LcdTest1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, MaterialTheme.colors.onPrimary, Color.Black)
    var colorIndex by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    val currentTestItem ="LCD Test 1"
    val device_spec_pdf = deviceSpecReportList(context)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "LCD Screen Test T1") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    NavigationPopButton(
                        navController = navController, testMode = testMode
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
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "LCD Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:LCDTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:LCDTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:LCDTest1", "nextPath: $nextPath")
                            Log.i("MyTag:LCDTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = if (nextPathString.isNotEmpty()) {
                                "$nextRoute/$nextPathString/$testMode"
                            } else {
                                nextRoute
                            }

                            navController.navigate(nextRouteWithArguments)
                        } else
                            navController.popBackStack()
                    }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "LCD Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        FailTestNavigator(
                            context = context,
                            onEvent = onEvent,
                            state = state,
                            navController = navController,
                            testMode = testMode,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem,
                            deviceSpec = device_spec_pdf
                        )
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:LCDTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:LCDTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:LCDTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:LCDTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = nextRoute
//                            }
//
//                            navController.navigate(nextRouteWithArguments)
//                        } else
//                            navController.popBackStack()
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
                text = "$colorIndex",
                color = Color.Black,
                fontSize = 40.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        colorIndex = (colorIndex + 1) % colors.size
//                        if (colorIndex == 0) {  // if we've looped back to the beginning
//                            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                                Log.i("MyTag:LCDTEST1", "pastRoute: $pastRoute")
//                                Log.i("MyTag:LCDTEST1", "nextTestRoute: $nextTestRoute")
//                                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                                val nextPath = nextTestRoute.drop(1)
//                                val nextPathString = nextPath.joinToString(separator = "->")
//                                Log.i("MyTag:LCDTEST1", "nextPath: $nextPath")
//                                Log.i("MyTag:LCDTEST1", "nextPathString: $nextPathString")
//
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
