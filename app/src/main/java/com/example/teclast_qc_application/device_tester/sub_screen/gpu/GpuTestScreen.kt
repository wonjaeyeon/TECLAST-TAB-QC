package com.example.teclast_qc_application.device_tester.sub_screen.gpu

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import com.example.teclast_qc_application.device_tester.sub_screen.gpu.tester.checkDeviceThermalStatus
import com.example.teclast_qc_application.device_tester.sub_screen.gpu.tester.gpu3DTest
import com.example.teclast_qc_application.device_tester.sub_screen.gpu.tester.gpuTest1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GpuTestScreen(context: Context, navController: NavController, ) {
    // Create a mutable state for battery health result
    val gpu2DCapacityTestResult = remember { mutableStateOf<String>("") }
    val gpu3DCapacityTestResult = remember { mutableStateOf<String>("") }
    val deviceThermalAfterTestState = remember { mutableStateOf<String>("") }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "GPU Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                    CoroutineScope(Dispatchers.Main).launch {gpu2DCapacityTestResult.value = gpuTest1()}
                }) {
                    Text(text = "gpu Test 1")
                }

                // Display battery health result
                Text(
                    text = gpu2DCapacityTestResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {gpu3DCapacityTestResult.value = gpu3DTest()}
                }) {
                    Text(text = "gpu Test 2")
                }

                // Display battery health result
                Text(
                    text = gpu3DCapacityTestResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {deviceThermalAfterTestState.value = checkDeviceThermalStatus(context = context)}
                }) {
                    Text(text = "Device Thermal After GPU Test")
                }

                Text(
                    text = deviceThermalAfterTestState.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )


            }
        }
    }
}