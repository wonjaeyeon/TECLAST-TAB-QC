package com.example.teclast_qc_application.device_tester.standard_test.standard_mode.sub_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StandardTestCompletedScreen(context: Context, navController: NavHostController, onExitApp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Test Completed") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.TaskAlt,
                contentDescription = "Completed",
                tint = Color.Green,
                modifier = Modifier.size(120.dp)
            )
            Text(text = "Test Finished", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    // Pop back to the root of the navigation stack, effectively clearing it.
                    navController.popBackStack(navController.graph.startDestinationId, false)
                },
                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
            ) {
                Text("Go Back to Main Page")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
//                    val activity: MainActivity = MainActivity()
//                    // on below line we are finishing activity.
//                    activity.finishAndRemoveTask()
//                    exitProcess(0)
                    onExitApp()
                },
                modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth()
            ) {
                Text("Save Test Result & Exit Application")
            }
        }
    }
}
