package com.example.teclast_qc_application.device_tester.specific_test.wifi

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teclast_qc_application.device_tester.specific_test.wifi.tester.fetchIpGeolocation
import com.example.teclast_qc_application.device_tester.specific_test.wifi.tester.getWifiConnectionStatus
import com.example.teclast_qc_application.device_tester.specific_test.wifi.tester.getWifiDataUsage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WifiTestScreen(context: Context,navController: NavController,viewModel: YourViewModel = viewModel() ) {
    // Create a mutable state for battery health result
    val connectionStateResult = remember { mutableStateOf<String>("") }
    val wifiDataUsageResult = remember { mutableStateOf<String>("") }
    // remember a mutable state
    val receivedMessage = remember { mutableStateOf("") }
    // val for HTTP Test
    val httpTestResult = remember { mutableStateOf("") }

    // get reference to the coroutine scope
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "WIFI Test") },
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
                    connectionStateResult.value = getWifiConnectionStatus(context)
                }) {
                    Text(text = "connection Test")
                }

                // Display battery health result
                Text(
                    text = connectionStateResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    wifiDataUsageResult.value = getWifiDataUsage(context).toString()
                }) {
                    Text(text = "Data Usage Test")
                }

                Text(
                    text = wifiDataUsageResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

//                Button(onClick = {
////                    runBlocking {
//                    wifiDataReceivedResult.value = getWifiDataReceive()
////                    }
//                }
//                ) {
//                    Text(text = "Data Receive Test")
//                }
//
//                Text(
//                    text = wifiDataReceivedResult.value,
//                    style = MaterialTheme.typography.body1,
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                    modifier = Modifier.padding(top = 16.dp))
//                Button(onClick = {
//                    scope.launch {
//                        wifiDataReceivedResult.value = getWifiDataReceive(context)
//                    }
//                }) {
//                    Text(text = "Data Receive Test")
//                }
//
                Button(onClick = {
                    // launch the client function in the coroutine
                    coroutineScope.launch {
                        receivedMessage.value = viewModel.startClient()
                    }
                }) {
                    Text("Receive Data(BROADCAST SERVER TEST)")
                }

                Text(text ="Received Message: ${receivedMessage.value}",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp))


                Button(onClick = {
                    // launch the client function in the coroutine
                    coroutineScope.launch {
                        httpTestResult.value = fetchIpGeolocation()
                    }
                }) {
                    Text("Receive Data(HTTP TEST)")
                }

                Text(text ="Received Message: ${httpTestResult.value}",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp))




            }
        }
    }
}

class YourViewModel : ViewModel() {

    suspend fun startClient(): String = withContext(Dispatchers.IO) {
        val port = 37020  // The same port used by the server

        DatagramSocket(port).use { socket ->
            socket.use {
                val buffer = ByteArray(1024)
                val packet = DatagramPacket(buffer, buffer.size)
                socket.receive(packet)

                val receivedMessage = String(packet.data, 0, packet.length)
                println("Received message: $receivedMessage from: ${packet.address}:${packet.port}")
                return@withContext receivedMessage
            }
        }
    }
}