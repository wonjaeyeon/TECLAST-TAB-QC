package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.usb.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Usb
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.DialogAPIInterface
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.TestAPIDialog
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UsbTestTestMode(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {

    val coroutineScope = rememberCoroutineScope()
    val usbHostModeStatus = remember { mutableStateOf("USB Host Mode Off") }
    val usbDevicesStatus = remember { mutableStateOf("No USB devices connected.") }
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status
    val showDialog = remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {

                usbDevicesStatus.value = usbTest1(state =state,onEvent = onEvent,context = context)
                delay(100)
                usbHostModeStatus.value = usbTest2(state =state,onEvent = onEvent,context = context)
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

                var nextRouteWithArguments: String
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
                    Log.i("MyTag:UsbTest", "nextRouteWithArguments: $nextRouteWithArguments")
                } else {
                    nextRouteWithArguments = nextRoute
                    Log.i("MyTag:UsbTest", "nextRouteWithArguments: $nextRouteWithArguments")
                }

                navController.navigate(nextRouteWithArguments)
                hasNavigated.value = true
            }  else{
                navController.popBackStack()
                hasNavigated.value = true
            }


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
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
                actions = {
                          DialogAPIInterface(
                              testMode = testMode,
                              showDialog = showDialog)
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    ) {
        TestAPIDialog(
            testMode = testMode,
            onEvent = onEvent,
            context = context,
            navController = navController,
            nextTestRoute = nextTestRoute,
            showDialog = showDialog
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Usb, contentDescription = "USB Status")
                Text(text = "Model : ${android.os.Build.MODEL}")
                //Text(text = "Usb Host Mode : ${usbHostModeStatus.value}")
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                if(usbDevicesStatus.value.startsWith("Usb TEST : Success")) {
                    Text(text = "Usb Host Mode On")
                    Text(text = "Card Reader : Available")
                } else
                    {
                        Text(text = "Usb Host Mode Off")
                        Text(text = "Card Reader : Not Available")
                    }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Text(text = usbMessage)

            }
        }
    }
}
