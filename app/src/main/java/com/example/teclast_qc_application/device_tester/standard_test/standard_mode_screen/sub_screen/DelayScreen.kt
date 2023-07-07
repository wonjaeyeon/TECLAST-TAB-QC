package com.example.teclast_qc_application.device_tester.standard_test.standard_mode_screen.sub_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestScreenScaffold(navController: NavHostController, progress: Float, done: List<String>, undone: List<String>, titleText: String = "TESTING Test", content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = titleText) },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            LinearProgressIndicator(
                progress,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(2/3f)
                ,color = Color.Green
            )

            Text(text = "done : ${done.joinToString(", ")}")
            Text(text = "undone : ${undone.joinToString(", ")}")

            // Call the provided composable content
            content()
        }
    }
}