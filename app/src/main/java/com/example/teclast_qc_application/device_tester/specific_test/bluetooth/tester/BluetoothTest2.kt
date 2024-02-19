package com.example.teclast_qc_application.device_tester.specific_test.bluetooth.tester

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun BluetoothTestT1(navController: NavController, context: Context) {
//    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
//    val devices = remember { mutableStateOf(listOf<BluetoothDevice>()) }
//
//    LaunchedEffect(Unit) {
//        devices.value = bluetoothAdapter.bondedDevices.toList()
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Bluetooth Tester") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        },
//        content = {
//            Column(modifier = Modifier.fillMaxSize()) {
//                Text("Paired devices:")
//                LazyColumn {
//                    items(devices.value) { device ->
//                        if (ActivityCompat.checkSelfPermission(context,
//                                Manifest.permission.BLUETOOTH_CONNECT
//                            ) != PackageManager.PERMISSION_GRANTED
//                        ) {
//
//                        }
//                        Text("Name: ${device.name}, Address: ${device.address}")
//                    }
//                }
//            }
//        }
//    )
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BluetoothTestT2(navController: NavController, context: Context) {
    val bluetoothAdapter = context.getSystemService(BluetoothManager::class.java)?.adapter
    val devices = remember { mutableStateOf(listOf<BluetoothDevice>()) }

    LaunchedEffect(Unit) {
        devices.value = bluetoothAdapter?.bondedDevices?.toList() ?: listOf()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bluetooth Tester") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
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


                Spacer(modifier = Modifier.padding(top = 40.dp))

                Text("Paired devices:")

                LazyColumn {
                    items(devices.value) { device ->
                        if (ActivityCompat.checkSelfPermission(context,
                                Manifest.permission.BLUETOOTH_CONNECT
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            // Request Bluetooth permissions
                        }
                        Text("Name: ${device.name}, Address: ${device.address}")
                    }
                }
            }
        }
    )
}

