package com.teclast_korea.teclast_qc_application.device_tester.specific_test.battery

//make a screen for cpu test

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.batteryTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.battery.tester.BatteryVoltageTest
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.battery.tester.batteryTestT2
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.battery.tester.checkDeviceThermalStatus
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BatteryTestScreen(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
) {
    // Create a mutable state for battery health result
    val batteryHealthResult = remember { mutableStateOf<String>("") }
    val batteryStateOfDevice = remember { mutableStateOf<String>("") }
    val deviceThermalStateAfterTest = remember { mutableStateOf<String>("") }
    val batteryVoltageResult = remember { mutableStateOf<String>("") }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Battery Test") },
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.onPrimary,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })

    }) {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primaryVariant)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)
            ) {
                // Battery Test Button
                Button(onClick = {
                    batteryHealthResult.value = batteryTestT1(state = state, onEvent = onEvent, context = context)
                }) {
                    Text(text = "Battery Test 1(Charged Percentage)(n/100)")
                }

                // Display battery health result
                Text(
                    text = batteryHealthResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                //make a button for battery test
                Button(onClick = {
                    batteryStateOfDevice.value = batteryTestT2(context)
                }) {
                    Text(text = "Charging State Test")

                }

                Text(
                    text = batteryStateOfDevice.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                //make a button for device thermal test after battery test
                Button(onClick = {
                    deviceThermalStateAfterTest.value = checkDeviceThermalStatus(context = context)
                }) {
                    Text(text = "Device Thermal Test")

                }
                Text(
                    text = deviceThermalStateAfterTest.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(
                    onClick = {
                        batteryVoltageResult.value = BatteryVoltageTest(context = context)
                    },

                    ) {
                    Text(text = "Battery voltage Test")
                }
                Text(
                    text = batteryVoltageResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}