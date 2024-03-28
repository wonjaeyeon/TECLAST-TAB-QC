package com.teclast_korea.teclast_qc_application.device_tester.specific_test.usb.tester

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

// Usb Test Each Port
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UsbTestEachPort(navController: NavController, context: Context) {
    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    val deviceList: HashMap<String, UsbDevice> = usbManager.deviceList
    val deviceNameList = remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        deviceNameList.value = deviceList.values.map { it.deviceName }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "USB Port Tester") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                Text("Connected USB Devices:")
                LazyColumn {
                    items(deviceNameList.value) { deviceName ->
                        Text(deviceName)
                    }
                }
            }
        }
    )
}


