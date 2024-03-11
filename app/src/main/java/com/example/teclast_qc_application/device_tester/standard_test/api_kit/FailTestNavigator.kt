package com.example.teclast_qc_application.device_tester.standard_test.api_kit

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.example.teclast_qc_application.home.pdf_export.generate_pdf.generatePDF
import com.example.teclast_qc_application.home.pdf_export.getDirectory
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File


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
    val endTestMode = listOf("FastMode", "T-OrderMode")
    val nextTestMode = listOf("StandardMode")
    val backTestMode = listOf("NotTestMode")



    if (testMode in endTestMode) {
        val deviceSpec = deviceSpec
        val File = File(getDirectory(context), "Test_Report.pdf")
        if (File.exists()) {
            File.delete()
        }
        Toast.makeText(context, "Test Is Stopped", Toast.LENGTH_SHORT).show()
        onEvent(TestResultEvent.SaveTestResult)
        onEvent(TestResultEvent.ClearPreviousTestResults)
        CoroutineScope(Dispatchers.IO).async {
            // Generate PDF
            Toast.makeText(context, "Generating Report", Toast.LENGTH_SHORT).show()

            generatePDF(
                context = context,
                directory = getDirectory(context),
                state = state,
                onEvent = onEvent,
                deviceSpec = deviceSpec,
                testMode = testMode,
            )
            Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
        }
        navController.navigate("fast_test_fail_screen/$testMode")
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
                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
            } else {
                nextRouteWithArguments = "${nextTestRoute[0]}"
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