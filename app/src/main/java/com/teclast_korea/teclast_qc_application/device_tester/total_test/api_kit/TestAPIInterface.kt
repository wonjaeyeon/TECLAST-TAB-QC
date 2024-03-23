package com.teclast_korea.teclast_qc_application.device_tester.total_test.api_kit

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.home.device_report.deviceSpecReportList
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState


@Composable
fun DialogAPIInterface(testMode: String, showDialog: MutableState<Boolean>) {
    if (testMode == "FastMode" || testMode.lowercase().contains("torder") || testMode.lowercase().contains("scspro")) {

        IconButton(onClick = {
            showDialog.value = true
        }) {
            Icon(
                imageVector = Icons.Outlined.Report,
                contentDescription = "Stop Test",
                tint = Color.Red,
                modifier = Modifier.size(36.dp)
            ) // Adjust the size as needed)
        }

    } else if (testMode == "StandardMode") {
        IconButton(onClick = { showDialog.value = true }) {
            Icon(
                imageVector = Icons.Outlined.SkipNext,
                contentDescription = "Stop Test",
                tint = Color.Green,
                modifier = Modifier.size(36.dp)
            ) // Adjust the size as needed)
        }

    } else if (testMode == "NotTestMode") {
        // Do nothing
    }
}


@Composable
fun TestAPIDialog(
    testMode: String,
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    nextTestRoute: MutableList<String>,
    showDialog: MutableState<Boolean>,
) {
    val deviceSpec = deviceSpecReportList(context = context)
    if (!showDialog.value) {
    }
    else if (testMode == "FastMode") {
        QuitTestDialog(
            context = context,
            navController = navController,
            state = state,
            onEvent = onEvent,
            showDeleteAlertDialog = showDialog,
            deviceSpec = deviceSpec,
            testMode = testMode,
        )
    } else if (testMode == "StandardMode") {
        SkipToNextTestDialog(
            context = context,
            navController = navController,
            nextTestRoute = nextTestRoute,
            state = state,
            onEvent = onEvent,
            showDeleteAlertDialog = showDialog,
            deviceSpec = deviceSpec,
            testMode = testMode
        )
    } else if (testMode.lowercase().contains("torder")
        || testMode.lowercase().contains("scspro")) {
        // Do nothing
        QuitTestDialog(
            context = context,
            navController = navController,
            state = state,
            onEvent = onEvent,
            showDeleteAlertDialog = showDialog,
            deviceSpec = deviceSpec,
            testMode = testMode,
        )
    } else{

    }
}