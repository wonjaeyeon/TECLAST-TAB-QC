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

//fun getBatteryState_v2(context: Context): String {
//    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
//        context.registerReceiver(null, filter)
//    }
//
//    val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
//
//    return when (status) {
//        BatteryManager.BATTERY_STATUS_CHARGING -> "Success : Charging"
//        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
//        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
//        BatteryManager.BATTERY_STATUS_FULL -> "Full"
//        BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
//        else -> "Error: Unable to determine battery status"
//    }
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BatteryTestT2(
    context: Context, navController: NavController, testMode: String = "StandardMode",
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

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {
                val batteryIntent: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
                    context.registerReceiver(null, filter)
                }
                batteryStatus.value = batteryIntent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
                delay(1000) // Check battery status every second
            }
        }

        onDispose {
            job.cancel()
        }
    }
    val batteryMessage = when (batteryStatus.value) {
        BatteryManager.BATTERY_STATUS_CHARGING -> {
            Log.i(testMode, "9. batteryTest2() is called : Battery TEST2 : Success : Charging")
            if (nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                Log.i("MyTag:BatteryTest", "pastRoute: $pastRoute")
                Log.i("MyTag:BatteryTest", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "/")
                Log.i("MyTag:BatteryTest", "nextPath: $nextPath")
                Log.i("MyTag:BatteryTest", "nextPathString: $nextPathString")

                var nextRouteWithArguments = "aaaa"
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                } else {
                    nextRouteWithArguments = "${nextTestRoute[0]}"
                }

                navController.navigate(nextRouteWithArguments)
            } else
                navController.popBackStack()


            "Success : Charging"
        }

        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Please charge the battery"
        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
        BatteryManager.BATTERY_STATUS_FULL -> {
            Log.i(testMode, "9. batteryTest2() is called : Battery TEST2 : Success : Charging")
            if (nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                Log.i("MyTag:BatteryTest", "pastRoute: $pastRoute")
                Log.i("MyTag:BatteryTest", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "/")
                Log.i("MyTag:BatteryTest", "nextPath: $nextPath")
                Log.i("MyTag:BatteryTest", "nextPathString: $nextPathString")

                var nextRouteWithArguments = "aaaa"
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                } else {
                    nextRouteWithArguments = "${nextTestRoute[0]}/notNextTest"
                }
                ///${param1}/${param2}
                navController.navigate(nextRouteWithArguments)
            } else
                navController.popBackStack()
            "Full"
        }

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
