package com.example.teclast_qc_application.device_tester.sub_screen.gpu

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
import com.example.teclast_qc_application.device_tester.sub_screen.wifi.tester.getWifiConnectionStatus


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GpuTestScreen(context: Context,navController: NavController, ) {
    // Create a mutable state for battery health result
    val gpuTemperatureStateResult = remember { mutableStateOf<String>("") }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "GPU Test") },
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
                    gpuTemperatureStateResult.value = getWifiConnectionStatus(context)
                }) {
                    Text(text = "gpu Temperature Test")
                }

                // Display battery health result
                Text(
                    text = gpuTemperatureStateResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

            }
        }
    }
}