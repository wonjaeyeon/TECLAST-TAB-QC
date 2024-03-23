package com.teclast_korea.teclast_qc_application.device_tester.total_test.api_kit

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState


fun FailTestNavigator(
    context: Context,
    onEvent: (TestResultEvent) -> Unit,
    state: TestResultState,
    testMode: String,
    navController: NavController,
    navigateToNextTest: Boolean,
    nextTestRoute: MutableList<String>,
    currentTestItem: String,
    deviceSpec: List<Pair<String, String>>
) {
    val endTestMode = listOf("FastMode", "TOrderMode","SCSPROMode")
    val nextTestMode = listOf("StandardMode")
    val backTestMode = listOf("NotTestMode")



    if (testMode in endTestMode) {
        onEvent(TestResultEvent.SaveTestResult)
//        val testResult = state.testResults.filter { it.itemName.contains(currentTestItem) }
//        while (testResult.isEmpty()){
//
//        AddTestResult(
//            state = state,
//            onEvent = onEvent,
//            currentTestItem,
//            "Fail",
//            Date().toString()
//        )
//        onEvent(TestResultEvent.SaveTestResult)}
        onEvent(TestResultEvent.ClearPreviousTestResults)
        // show me the current DB data
//        Log.i(
//            "FailTestNavigator_TestDB",
//            TestReportList(context = context, state = state, onEvent = onEvent).toString()
//        )
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

            var nextRouteWithArguments = ""
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