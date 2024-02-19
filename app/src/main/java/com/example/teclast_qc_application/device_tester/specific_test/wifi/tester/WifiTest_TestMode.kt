package com.example.teclast_qc_application.device_tester.specific_test.wifi.tester

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WifiTestTestMode(
    context: Context,
    navController: NavController,
    runningTestMode: Boolean = false,
    testMode: String = "StandardMode",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf()
) {
    val coroutineScope = rememberCoroutineScope()
    val wifiConnectedStatus = remember { mutableStateOf(getWifiConnectionStatus(context)) }
    val wifiSignalStrength = remember { mutableStateOf(getWifiSignalStrength(context)) }
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status


    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {
                wifiConnectedStatus.value = getWifiConnectionStatus(context)
                wifiSignalStrength.value = getWifiSignalStrength(context)
                delay(100) // Check wifi status every second
            }
        }
        onDispose {
            job.cancel()
        }
    }

    val wifiMessage = if (wifiConnectedStatus.value.startsWith("Connected to Wi-Fi") && wifiSignalStrength.value > 20) {

        if(!hasNavigated.value) {
            Log.i(testMode, "12. getWifiConnectionStatus() is called : Success : Wifi is connected")
            Log.i(testMode, "13. getWifiSignalStrength() is called : Success : Wifi signal strength is $wifiSignalStrength")

            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0)
                Log.i("MyTag:WifiTest", "pastRoute: $pastRoute")
                Log.i("MyTag:WifiTest", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0]
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "->")
                Log.i("MyTag:WifiTest", "nextPath: $nextPath")
                Log.i("MyTag:WifiTest", "nextPathString: $nextPathString")

                var nextRouteWithArguments = "aaaa"
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                    Log.i("MyTag:WifiTest", "nextRouteWithArguments: $nextRouteWithArguments")
                } else {
                    nextRouteWithArguments = "${nextTestRoute[0]}"
                    Log.i("MyTag:WifiTest", "nextRouteWithArguments: $nextRouteWithArguments")
                }

                navController.navigate(nextRouteWithArguments)
                hasNavigated.value = true
            } else if (runningTestMode) {
                onTestComplete()
                hasNavigated.value = true
            } else
                navController.popBackStack()
            hasNavigated.value = true

            "Success : Wifi is connected with signal strength $wifiSignalStrength"
        }else{
            "Success : Wifi is connected with signal strength $wifiSignalStrength"
        }

    } else {
        "Wifi is not connected"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Wifi Test") },
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
                Icon(Icons.Filled.Wifi, contentDescription = "Wifi Status")
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
                        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                        context.startActivity(intent)
                    }) {
                        Text("Go to WI-FI settings")
                    }
                }
                Text(text = "Model : ${android.os.Build.MODEL}")
                Text(text = wifiMessage)
//                if(wifiConnectedStatus.value.startsWith("Connected to Wi-Fi")){
//                    Text(text = "SSID : Success")
//                }
//                else{
//                    Text(text = "SSID : N/A")
//                }


            }
        }
    }
}