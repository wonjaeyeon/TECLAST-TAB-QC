package com.teclast_korea.teclast_qc_application.ui.router.api_kit

import android.util.Log
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent


fun FailTestNavigator(
    onEvent: (TestResultEvent) -> Unit,
    testMode: String,
    navController: NavController,
    navigateToNextTest: Boolean,
    nextTestRoute: MutableList<String>,
    currentTestItem: String
) {
    val endTestMode = listOf("FastMode", "TOrderMode","SCSPROMode")
    val nextTestMode = listOf("StandardMode")
    val backTestMode = listOf("NotTestMode")



    if (testMode in endTestMode) {
        onEvent(TestResultEvent.SaveTestResult)
        onEvent(TestResultEvent.ClearPreviousTestResults)

        var deviceNavID = ""
        when (testMode) {
            "FastMode" -> {
                deviceNavID = "fast_test_fail_screen"
            }
            "TOrderMode" -> {
                deviceNavID = "t_order_test_fail_screen"
            }
            "SCSPROMode" -> {
                deviceNavID = "scspro_test_fail_screen"
            }
        }
        navController.navigate("$deviceNavID/$testMode")
    } else if (testMode in nextTestMode) {
        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
            Log.i("MyTag:$currentTestItem", "pastRoute: $pastRoute")
            Log.i("MyTag:$currentTestItem", "nextTestRoute: $nextTestRoute")
            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
            val nextPath = nextTestRoute.drop(1)
            val nextPathString = nextPath.joinToString(separator = "->")
            Log.i("MyTag:$currentTestItem", "nextPath: $nextPath")
            Log.i("MyTag:$currentTestItem", "nextPathString: $nextPathString")

            var nextRouteWithArguments: String
            if (nextPathString.isNotEmpty()) {
                nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
            } else {
                nextRouteWithArguments = nextRoute
            }

            navController.navigate(nextRouteWithArguments)
        } else {
            navController.popBackStack()
        }
    } else if (testMode in backTestMode) {
        navController.popBackStack()
    } else {
        Log.i("MyTag:FailTestNavigator", "Next Route Not Found")
    }
}