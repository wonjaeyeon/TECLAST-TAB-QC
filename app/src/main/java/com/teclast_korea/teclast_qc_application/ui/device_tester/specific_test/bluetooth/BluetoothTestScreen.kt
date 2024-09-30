package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.bluetooth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.bluetooth.tester.BluetoothTestT1


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BluetoothTestScreen(context: Context, navController: NavController, ) {
    val bluetoothStateResult = remember{ mutableStateOf<String>("")}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bluetooth Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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

                // Bluetooth Test t1 Button
                Button(onClick = {
                    bluetoothStateResult.value = BluetoothTestT1()
                }){
                    Text(text = "Bluetooth Test 1")
                }

                Text(
                    text = bluetoothStateResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )



                // Bluetooth Screen Test t2 Button
                Button(onClick = {
                    navController.navigate("bluetooth_test_t2_screen"){

                    }
                }) {
                    Text(text = "BlueTooth Test 2")
                }

            }
        }
    }
}