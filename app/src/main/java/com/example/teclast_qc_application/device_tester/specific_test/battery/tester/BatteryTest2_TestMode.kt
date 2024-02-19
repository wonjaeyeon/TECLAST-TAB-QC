package com.example.teclast_qc_application.device_tester.specific_test.battery.tester

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BatteryTestTestMode(
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    testMode: String = "StandardMode",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {
//    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
//        context.registerReceiver(null, filter)
//    }
//
//    val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
//    val batteryMessage = when (status) {
//        BatteryManager.BATTERY_STATUS_CHARGING -> {
//            Log.i("Battery Status", "Success : Charging")
//            navController.popBackStack()
//            "Success : Charging"
//        }
//        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Please charge the battery"
//        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
//        BatteryManager.BATTERY_STATUS_FULL -> "Full"
//        BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
//        else -> "Error: Unable to determine battery status"
//    }
    val coroutineScope = rememberCoroutineScope()
    val batteryStatus = remember { mutableStateOf(BatteryManager.BATTERY_STATUS_UNKNOWN) }
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {
                val batteryIntent: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
                    context.registerReceiver(null, filter)
                }
                batteryStatus.value = batteryIntent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
                delay(100) // Check battery status every second
            }
        }

        onDispose {
            job.cancel()
        }
    }
    val batteryMessage = when (batteryStatus.value) {
        BatteryManager.BATTERY_STATUS_CHARGING, BatteryManager.BATTERY_STATUS_FULL -> {
            if (!hasNavigated.value) {
                Log.i(testMode, "9. batteryTest2() is called : Battery TEST2 : Success : Charging")
                if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                    val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                    Log.i("MyTag:BatteryTest", "pastRoute: $pastRoute")
                    Log.i("MyTag:BatteryTest", "nextTestRoute: $nextTestRoute")
                    val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                    val nextPath = nextTestRoute.drop(1)
                    val nextPathString = nextPath.joinToString(separator = "->")
                    Log.i("MyTag:BatteryTest", "nextPath: $nextPath")
                    Log.i("MyTag:BatteryTest", "nextPathString: $nextPathString")

                    var nextRouteWithArguments = "aaaa"
                    if (nextPathString.isNotEmpty()) {
                        nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                        Log.i("MyTag:BatteryTest", "nextRouteWithArguments: $nextRouteWithArguments")
                    } else {
                        nextRouteWithArguments = "${nextTestRoute[0]}"
                        Log.i("MyTag:BatteryTest", "nextRouteWithArguments: $nextRouteWithArguments")
                    }

                    navController.navigate(nextRouteWithArguments)
                    hasNavigated.value = true
                } else if (runningTestMode) {
                    onTestComplete()
                    hasNavigated.value = true
                } else
                    navController.popBackStack()
                hasNavigated.value = true


                "Success : Charging"
            } else {
                "Success : Charging"
            }
        }

        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Please charge the battery"
        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
//        BatteryManager.BATTERY_STATUS_FULL -> {
//            Log.i(testMode, "9. batteryTest2() is called : Battery TEST2 : Success : Charging")
//            if (nextTestRoute.isNotEmpty()) {
//                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = BatteryTest2
//                Log.i("MyTag:BatteryTest", "pastRoute: $pastRoute")
//                Log.i("MyTag:BatteryTest", "nextTestRoute: $nextTestRoute")
//                val nextPath = nextTestRoute.drop(1)
//                val nextPathString = nextPath.joinToString(separator = "/")
//                Log.i("MyTag:BatteryTest", "nextPath: $nextPath")
//                Log.i("MyTag:BatteryTest", "nextPathString: $nextPathString")
//
//                var nextRouteWithArguments = "aaaa"
//                if (nextPathString.isNotEmpty()) {
//                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
//                    Log.i("MyTag:BatteryTest", "nextRouteWithArguments: $nextRouteWithArguments")
//                } else {
//                    nextRouteWithArguments = "${nextTestRoute[0]}/notNextTest"
//                    Log.i("MyTag:BatteryTest", "nextRouteWithArguments: $nextRouteWithArguments")
//                }
//                ///${param1}/${param2}
//                navController.navigate("lcd_screen_test_t1_screen/lcd_screen_test_t2_screen")
//            } else
//                navController.popBackStack()
//            "Full"
//        }

        BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
        else -> "Error: Unable to determine battery status"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Battery Charging Test") },
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
                Icon(Icons.Filled.BatteryAlert, contentDescription = "Battery Status")
                Text(text = batteryMessage)
            }
        }
    }
}
