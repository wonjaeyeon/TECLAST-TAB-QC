package com.example.teclast_qc_application.device_tester.specific_test.auto_sleep.tester

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AutoSleepTestT1(context: Context, navController: NavController) {
    // Get a reference to the current window.
    val window = LocalContext.current as Activity

    // Use remember to create a state variable that will survive
    // recompositions, and initialize it to false.
    val keepAwake = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Auto Sleep Test") },
                navigationIcon = {
                    IconButton(onClick = {
                        window.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                        navController.popBackStack()

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(), // Fill the parent size to allow centering
                verticalArrangement = Arrangement.Center, // Center content vertically
                horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
            ) {
                Text("Keep the screen on while this screen is visible?")
                Text("If turned on, the screen will not turn off automatically.")
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Screen Awake: " + if (keepAwake.value) "Turn On" else "Turn Off",
                    style = MaterialTheme.typography.body1.copy(fontSize = 20.sp) // Custom text size
                )
                Spacer(modifier = Modifier.height(20.dp))
                Switch(
                    checked = keepAwake.value,
                    onCheckedChange = { isChecked ->
                        keepAwake.value = isChecked
                        if (isChecked) {
                            window.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                        } else {
                            window.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                        }
                    }
                )
            }
        }
    )
}