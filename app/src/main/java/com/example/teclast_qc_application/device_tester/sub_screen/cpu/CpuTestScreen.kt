package com.example.teclast_qc_application.device_tester.sub_screen.cpu

//make a screen for cpu test
import android.annotation.SuppressLint
import android.content.Context
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
import com.example.teclast_qc_application.checkBatteryHealth
import com.example.teclast_qc_application.device_tester.sub_screen.cpu.tester.getAppUsageStats
import com.example.teclast_qc_application.device_tester.sub_screen.cpu.tester.getTemperatureInCelsius2


@RequiresApi(34)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CpuTestScreen(context: Context,navController: NavController, ){

    // Create a mutable state for battery health result
    val batteryHealthResult = remember { mutableStateOf<String>("") }
    val batteryStateofdevice = remember { mutableStateOf<String>("") }
    val cpuStateofdevice = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "CPU Test") },
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
                    val isBatteryHealthGood = checkBatteryHealth(context)
                    batteryHealthResult.value = if (isBatteryHealthGood) {
                        "Battery health is good."
                    } else {
                        "Battery health is below the threshold."
                    }
                }) {
                    Text(text = "Battery Test")
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
                //make a button for battery test
                Button(onClick = {
                    batteryStateofdevice.value = getTemperatureInCelsius2(context = context).toString()
                }) {
                    Text(text = "CPU Temperature Test")

                }

                Text(
                    text = batteryStateofdevice.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                //make a button for cpu test
                Button(onClick = {
                    cpuStateofdevice.value = getAppUsageStats(context = context).toString()
                }) {
                    Text(text = "CPU Test")

                }
                Text(
                    text = cpuStateofdevice.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
    println(batteryStateofdevice.value)

}