package com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LcdScreenTest(context: Context, navController: NavController, ) {
    // Create a mutable state for battery health result
    val lcdTestResult = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "LCD Screen Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
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
                // LCD Screen Test t1 Button
                Button(onClick = {
                    navController.navigate("lcd_screen_test_t1_screen/notNextTest"){
//                        popUpTo("touch_panel_test_screen"){
//                            inclusive = true
//
//                        }
                    }
                }) {
                    Text(text = "LCD Screen Test 1")
                }

                // LCD Screen Test t2 Button
                Button(onClick = {
                    navController.navigate("lcd_screen_test_t2_screen/notNextTest"){
//                        popUpTo("touch_panel_test_screen"){
//                            inclusive = true
//
//                        }
                    }
                }) {
                    Text(text = "LCD Screen Test 2")
                }

                // Display battery health result
                Text(
                    text = lcdTestResult.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

            }
        }
    }
}