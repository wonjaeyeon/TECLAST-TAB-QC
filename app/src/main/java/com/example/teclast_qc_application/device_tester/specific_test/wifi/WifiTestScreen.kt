package com.example.teclast_qc_application.device_tester.specific_test.wifi

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.teclast_qc_application.device_tester.specific_test.wifi.tester.getWifiConnectionStatus
import com.example.teclast_qc_application.device_tester.specific_test.wifi.tester.getWifiDataUsage


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WifiTestScreen(context: Context,navController: NavController, ) {
    // Create a mutable state for battery health result
    val connectionStateResult = remember { mutableStateOf<String>("") }
    val wifiDataUsageResult = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "WIFI Test") },
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
                    connectionStateResult.value = getWifiConnectionStatus(context)
                }) {
                    Text(text = "connection Test")
                }

                // Display battery health result
                Text(
                    text = connectionStateResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    wifiDataUsageResult.value = getWifiDataUsage(context).toString()
                }) {
                    Text(text = "Data Usage Test")
                }

                Text(
                    text = wifiDataUsageResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

            }
        }
    }
}