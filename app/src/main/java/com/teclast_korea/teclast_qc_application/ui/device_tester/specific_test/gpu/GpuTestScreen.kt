package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.gpu

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.device_thermal.tester.checkDeviceThermalStatus
import com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.gpu.tester.gpu3DTest
import com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.gpu.tester.gpuTest1
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results_db.TestResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GpuTestScreen(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavController,
) {
    // Create a mutable state for battery health result
    val gpu2DCapacityTestResult = remember { mutableStateOf<String>("") }
    val gpu3DCapacityTestResult = remember { mutableStateOf<String>("") }
    val deviceThermalAfterTestState = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GPU Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                .background(MaterialTheme.colors.primaryVariant)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                // Battery Test Button
                Button(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        gpu2DCapacityTestResult.value = gpuTest1(state = state, onEvent = onEvent)
                    }
                }) {
                    Text(text = "gpu Test 1")
                }

                // Display battery health result
                Text(
                    text = gpu2DCapacityTestResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        gpu3DCapacityTestResult.value = gpu3DTest(state = state, onEvent = onEvent)
                    }
                }) {
                    Text(text = "gpu Test 2")
                }

                // Display battery health result
                Text(
                    text = gpu3DCapacityTestResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        deviceThermalAfterTestState.value = checkDeviceThermalStatus(context = context)
                    }
                }) {
                    Text(text = "Device Thermal After GPU Test")
                }

                Text(
                    text = deviceThermalAfterTestState.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

            }
        }
    }
}