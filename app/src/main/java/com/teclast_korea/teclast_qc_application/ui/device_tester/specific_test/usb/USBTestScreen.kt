package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.usb


import android.annotation.SuppressLint
import android.content.Context
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
import com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.usb.tester.usbTest1
import com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.usb.tester.usbTest2
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.test_results.TestResultState


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun usbTestScreen(state: TestResultState,
                  onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavController, ) {
    // Create a mutable state for battery health result
    val connectionStateResult = remember { mutableStateOf<String>("") }
    val usbHostModeAvailabilityState = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "USB Test") },
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
                // USB Test Button
                Button(onClick = {
                    connectionStateResult.value = usbTest1(state, onEvent, context)
                }) {
                    Text(text = "connection Test")
                }

                // Display battery health result
                Text(
                    text = connectionStateResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Card Reader Test Button
                Button(onClick = {
                    usbHostModeAvailabilityState.value = usbTest2(state, onEvent, context)
                }) {
                    Text(text = "USB Host Mode availablity Test")
                }

                // Display battery health result
                Text(
                    text = usbHostModeAvailabilityState.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Audio Screen Test t1 Button
                Button(onClick = {
                    navController.navigate("usb_test_each_port_screen"){
                    }
                }) {
                    Text(text = "USB Each Port Test 1")
                }

            }
        }
    }
}