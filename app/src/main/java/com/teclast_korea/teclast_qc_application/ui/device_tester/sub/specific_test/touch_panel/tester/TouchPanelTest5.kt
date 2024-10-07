package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.touch_panel.tester

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

class TouchPanelViewModel : ViewModel() {
    // Shared mutable state variables

    val serialNumber = mutableStateOf("")
    val wifiMacAddress = mutableStateOf("")
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelTest5(context: Context, navController: NavController) {
    val viewModel: TouchPanelViewModel = viewModel()  // Access ViewModel
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Test T5") },
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
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(onClick = {
                    val intent = Intent(Settings.ACTION_DEVICE_INFO_SETTINGS)
                    context.startActivity(intent)
                }) {
                    Text("Go to settings")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.serialNumber.value,
                    onValueChange = { viewModel.serialNumber.value = it },
                    label = { Text("Enter serial number") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val intent = Intent(Settings.ACTION_DEVICE_INFO_SETTINGS)
                    context.startActivity(intent)
                }) {
                    Text("Go to settings")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.wifiMacAddress.value,
                    onValueChange = { viewModel.wifiMacAddress.value = it },
                    label = { Text("Enter wifi MAC Address") }
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(onClick = {

                }) {
                    Text("Save")
                }
            }
        }

    }
}
