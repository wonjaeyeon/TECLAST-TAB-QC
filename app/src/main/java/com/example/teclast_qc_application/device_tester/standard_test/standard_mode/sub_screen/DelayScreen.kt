package com.example.teclast_qc_application.device_tester.standard_test.standard_mode.sub_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.device_tester.standard_test.api_kit.DialogAPIInterface
import com.example.teclast_qc_application.device_tester.standard_test.api_kit.TestAPIDialog
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StandardModeTestScreenScaffold(
    navController: NavHostController,
    nextTestRoute: MutableList<String>,
    progress: Float,
    done: List<String>,
    undone: List<String>,
    titleText: String = "Standard Mode Test",
    content: @Composable () -> Unit,
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context
) {
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = titleText) },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
                actions = {
                    DialogAPIInterface(
                        testMode = "StandardMode",
                        showDialog = showDialog
                    )
                },
            )
        }
    ) {

        TestAPIDialog(
            testMode = "StandardMode",
            state = state,
            onEvent = onEvent,
            context = context,
            navController = navController,
            nextTestRoute = nextTestRoute,
            showDialog = showDialog
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            LinearProgressIndicator(
                progress,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(2 / 3f), color = Color.Green
            )

            Text(text = "done : ${done.joinToString(", ")}")
            Text(text = "undone : ${undone.joinToString(", ")}")

            // Call the provided composable content
            content()
        }
    }
}

