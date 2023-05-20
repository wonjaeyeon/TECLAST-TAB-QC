package com.example.teclast_qc_application.device_tester.sub_screen.auto_sleep.tester

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import java.util.*


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
                }
            )
        },
        content = {
            Column {
                Text("Keep the screen on while this screen is visible?")
                Text("If turned on, the screen will not turn off automatically.")
                Text("Screen Awake: ${keepAwake.value}")
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