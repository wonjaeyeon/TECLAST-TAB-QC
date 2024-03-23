package com.teclast_korea.teclast_qc_application.device_tester.specific_test.wifi.tester

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.device_tester.total_test.api_kit.DialogAPIInterface
import com.teclast_korea.teclast_qc_application.device_tester.total_test.api_kit.TestAPIDialog
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WifiTestTestMode(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf(),
    targetWifiSignalStrength: MutableState<Int> = mutableStateOf(80)
) {
    val coroutineScope = rememberCoroutineScope()
    val wifiConnectedStatus = remember { mutableStateOf(wifiConnectionTest(state, onEvent, context)) }
    val targetWifiSignalStrength = remember { targetWifiSignalStrength }
    val wifiSignalStrength =
        remember { mutableStateOf(wifiSignalStrengthTest(state, onEvent, context, targetWifiSignalStrength)) }
    val hasNavigated = remember { mutableStateOf(false) }  // State to track navigation status
    val showDialog = remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {
                wifiConnectedStatus.value = wifiConnectionTest(state, onEvent, context)
                delay(100)
                wifiSignalStrength.value = wifiSignalStrengthTest(state, onEvent, context, targetWifiSignalStrength)
                delay(100) // Check Wi-Fi status every second
            }
        }
        onDispose {
            job.cancel()
        }
    }

    val wifiMessage =
        if (wifiConnectedStatus.value.startsWith("Connected to Wi-Fi") && wifiSignalStrength.value.startsWith("WIFI : Pass")) {

            if (!hasNavigated.value) {
                Log.i(testMode, "12. getWifiConnectionStatus() is called : Success : Wifi is connected")
                Log.i(
                    testMode,
                    "13. getWifiSignalStrength() is called : Success : Wifi signal strength is $wifiSignalStrength"
                )

                if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                    // Test Mode인 경우
                    // nextTestRoute : 바로 다음 경로 + 그 다음 경로들 포함
                    // 예) nextTestRoute = ["test1", "test2", "test3"] 이면
                    // 바로 경로 : test1
                    // 다음 경로 : test2 -> test3
                    // nextPathString : test2 -> test3
                    val pastRoute = nextTestRoute.removeAt(0)
                    Log.i("MyTag:WifiTest", "pastRoute: $pastRoute")
                    Log.i("MyTag:WifiTest", "nextTestRoute: $nextTestRoute")
                    val nextRoute = nextTestRoute[0]
                    val nextPath = nextTestRoute.drop(1)
                    val nextPathString = nextPath.joinToString(separator = "->")
                    Log.i("MyTag:WifiTest", "nextPath: $nextPath")
                    Log.i("MyTag:WifiTest", "nextPathString: $nextPathString")

                    var nextRouteWithArguments = ""
                    if (nextPathString.isNotEmpty()) {
                        nextRouteWithArguments = "$nextRoute/$nextPathString/$testMode"
                        Log.i("MyTag:WifiTest", "nextRouteWithArguments: $nextRouteWithArguments")
                    } else {
                        nextRouteWithArguments = nextRoute
                        Log.i("MyTag:WifiTest", "nextRouteWithArguments: $nextRouteWithArguments")
                    }

                    navController.navigate(nextRouteWithArguments)
                    hasNavigated.value = true
                } else {
                    //Test Mode가 아닌 경우
                    navController.popBackStack()
                    hasNavigated.value = true
                }

                "Wifi is connected : Success\nsignal strength : Success"
            } else {
                "Wifi is connected : Success\nsignal strength : Success"
            }

        } else {
            if (wifiConnectedStatus.value.startsWith("Connected to Wi-Fi") && wifiSignalStrength.value.startsWith("WIFI : Fail")) {
                "Wifi is connected : Success \nsignal strength : ${getWifiSignalStrength(context)} / 100"
            } else {
                "Wifi is not connected : Fail"
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Wifi Test") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
                actions = {
                    // Button with Stop Icon
                    DialogAPIInterface(
                        testMode = testMode,
                        showDialog = showDialog
                    )

                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }

    ) {

        TestAPIDialog(
            testMode = testMode,
            state = state,
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
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
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