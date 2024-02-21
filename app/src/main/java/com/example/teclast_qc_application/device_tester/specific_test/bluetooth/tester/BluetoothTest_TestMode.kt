package com.example.teclast_qc_application.device_tester.specific_test.bluetooth.tester

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResultV2
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BluetoothTestTestMode(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    testMode: String = "StandardMode",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf()
) {
    val coroutineScope = rememberCoroutineScope()
    val bluetoothConnectedStatus = remember { mutableStateOf(BluetoothTestT1()) }
    val bluetoothConnectedDevices = remember { mutableStateOf(BluetoothTestT2_v4(context)) }
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status
    val hasAddedTestResult = remember { mutableStateOf(false) }  // State to track result addition status


    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {
                bluetoothConnectedStatus.value = BluetoothTestT1()
                bluetoothConnectedDevices.value = BluetoothTestT2_v4(context)
                delay(100) // Check bluetooth status every second
            }
        }

        onDispose {
            job.cancel()
        }
    }

    val bluetoothMessage =
        if (bluetoothConnectedStatus.value.startsWith("Bluetooth Test : Success : Bluetooth is enabled") && bluetoothConnectedDevices.value.isNotEmpty()) {
            if (!hasNavigated.value) {
                Log.i(testMode, "14. BluetoothTestT1() is called : Success : Bluetooth is enabled")
                Log.i(
                    testMode,
                    "15. BluetoothTestT2_v4() is called : Success : Bluetooth is enabled with bluetooth device\n" +
                            " ${bluetoothConnectedStatus.value}"
                )

                if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                    val pastRoute = nextTestRoute.removeAt(0)
                    Log.i("MyTag:BluetoothTest", "pastRoute: $pastRoute")
                    Log.i("MyTag:BluetoothTest", "nextTestRoute: $nextTestRoute")
                    val nextRoute = nextTestRoute[0]
                    val nextPath = nextTestRoute.drop(1)
                    val nextPathString = nextPath.joinToString(separator = "->")
                    Log.i("MyTag:BluetoothTest", "nextPath: $nextPath")
                    Log.i("MyTag:BluetoothTest", "nextPathString: $nextPathString")

                    var nextRouteWithArguments = "aaaa"
                    if (nextPathString.isNotEmpty()) {
                        nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                        Log.i("MyTag:BluetoothTest", "nextRouteWithArguments: $nextRouteWithArguments")
                    } else {
                        nextRouteWithArguments = "${nextTestRoute[0]}"
                        Log.i("MyTag:BluetoothTest", "nextRouteWithArguments: $nextRouteWithArguments")
                    }
                    if (!hasNavigated.value) {
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResultV2(
                            state = state,
                            onEvent = onEvent,
                            "Bluetooth Test",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                    }

                    navController.navigate(nextRouteWithArguments)
                    hasNavigated.value = true
                } else if (runningTestMode) {
                    onTestComplete()
                    if (!hasNavigated.value) {
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResultV2(
                            state = state,
                            onEvent = onEvent,
                            "Battery Test 2",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                    }
                    hasNavigated.value = true
                } else {
                    if (!hasNavigated.value) {
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResultV2(
                            state = state,
                            onEvent = onEvent,
                            "Bluetooth Test",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                    }
                    navController.popBackStack()
                }
                hasNavigated.value = true

                "Success : Bluetooth is enabled with bluetooth device\n ${bluetoothConnectedStatus.value}"
            } else {
                "Success : Bluetooth is enabled with bluetooth device\n ${bluetoothConnectedStatus.value}"
            }

        } else {
            if (!hasAddedTestResult.value) {
                onEvent(TestResultEvent.SaveTestResult)
                AddTestResultV2(
                    state = state,
                    onEvent = onEvent,
                    "Bluetooth Test",
                    "Fail",
                    Date().toString()
                )
                onEvent(TestResultEvent.SaveTestResult)
                hasAddedTestResult.value = true
            }
            "Bluetooth is not connected"
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bluetooth Test") },
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
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Bluetooth, contentDescription = "Bluetooth Status")
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        //tint = MaterialTheme.colors.primary,
                        contentDescription = "Back"
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    Button(onClick = {
                        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
                        context.startActivity(intent)
                    }) {
                        Text("Go to Bluetooth settings")
                    }
                }
                Text(text = "Model : ${android.os.Build.MODEL}")
                Text(text = bluetoothMessage)

            }
        }
    }
}