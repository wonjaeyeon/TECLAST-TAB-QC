package com.example.teclast_qc_application.device_tester.specific_test.battery

//make a screen for cpu test

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.batteryTestT1
import com.example.teclast_qc_application.device_tester.specific_test.battery.tester.batteryTestT2
import com.example.teclast_qc_application.device_tester.specific_test.battery.tester.checkDeviceThermalStatus


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BatteryTestScreen(context: Context,navController: NavController, ) {
    // Create a mutable state for battery health result
    val batteryHealthResult = remember { mutableStateOf<String>("") }
    val batteryStateofdevice = remember { mutableStateOf<String>("") }
    val deviceThermalStateAfterTest = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Battery Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
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
                    batteryHealthResult.value = batteryTestT1(context)
                }) {
                    Text(text = "Battery Test 1(Charged Percentage)(n/100)")
                }

                // Display battery health result
                Text(
                    text = batteryHealthResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                //make a button for battery test
                Button(onClick = {
                    batteryStateofdevice.value = batteryTestT2(context)
                }) {
                    Text(text = "Charing State Test")

                }

                Text(
                    text = batteryStateofdevice.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
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
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}