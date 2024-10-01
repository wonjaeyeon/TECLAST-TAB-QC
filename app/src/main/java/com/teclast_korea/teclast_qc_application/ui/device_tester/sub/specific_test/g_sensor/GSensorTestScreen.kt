package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.g_sensor



import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GSensorTestScreen(navController: NavController, ) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "G-Sensor Test") },
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
                // G Sensor Screen Test t1 Button
                Button(onClick = {
                    navController.navigate("g_sensor_test_t1_screen/notNextTest/NotTestMode"){
//                        popUpTo("touch_panel_test_screen"){
//                            inclusive = true
//
//                        }
                    }
                }) {
                    Text(text = "G-Sensor Test 1")
                }

            }
        }
    }
}