package com.example.teclast_qc_application.device_tester.specific_test.usb.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Usb
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UsbTest1TestMode(
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    testMode: String = "StandardMode",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {

    val coroutineScope = rememberCoroutineScope()
    val usbHostModeStatus = remember { mutableStateOf(usbTest2(context)) }
    val usbDevicesStatus = remember { mutableStateOf("No USB devices connected.") }
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {

                usbDevicesStatus.value = usbTest1(context)
//                usbDevicesStatus.value = "Usb TEST : Success : Device ID: 111, Manufacturer: apple, Product: macbook\n"


                delay(100) // Check USB devices every second
            }
        }

        onDispose {
            job.cancel()
        }
    }

    val usbMessage = if (usbDevicesStatus.value.startsWith("Usb TEST : Success") && usbHostModeStatus.value.startsWith("Usb TEST : Success")) {
        if (!hasNavigated.value) {
            Log.i(testMode, "10. usbTest1() is called : USB TEST1 : Success : USB Device Detected")
            Log.i(testMode, "11. usbTest2() is called : USB TEST2 : Success : USB Host Mode Detected")

            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0)
                Log.i("MyTag:UsbTest", "pastRoute: $pastRoute")
                Log.i("MyTag:UsbTest", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0]
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "->")
                Log.i("MyTag:UsbTest", "nextPath: $nextPath")
                Log.i("MyTag:UsbTest", "nextPathString: $nextPathString")

                var nextRouteWithArguments = "aaaa"
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                    Log.i("MyTag:UsbTest", "nextRouteWithArguments: $nextRouteWithArguments")
                } else {
                    nextRouteWithArguments = "${nextTestRoute[0]}"
                    Log.i("MyTag:UsbTest", "nextRouteWithArguments: $nextRouteWithArguments")
                }

                navController.navigate(nextRouteWithArguments)
                hasNavigated.value = true
            } else if (runningTestMode) {
                onTestComplete()
                hasNavigated.value = true
            } else
                navController.popBackStack()
            hasNavigated.value = true

            "Success : USB Device Detected"
        } else {
            "Success : USB Device Detected"
        }
    } else {
        usbDevicesStatus.value
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Usb Test 1") },
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
                Icon(Icons.Filled.Usb, contentDescription = "USB Status")
                Text(text = "Model : ${android.os.Build.MODEL}")
                Text(text = "Usb Host Mode : ${usbHostModeStatus.value}")
                Text(text = usbMessage)

            }
        }
    }
}
