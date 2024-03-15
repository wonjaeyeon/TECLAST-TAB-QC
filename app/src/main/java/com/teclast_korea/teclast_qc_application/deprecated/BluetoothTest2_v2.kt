package com.teclast_korea.teclast_qc_application.deprecated

//import android.Manifest
//import android.annotation.SuppressLint
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothDevice
//import android.content.Context
//import android.content.pm.PackageManager
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.core.app.ActivityCompat
//import androidx.navigation.NavController
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun BluetoothTestT2_ver2(navController: NavController, context: Context) {
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
