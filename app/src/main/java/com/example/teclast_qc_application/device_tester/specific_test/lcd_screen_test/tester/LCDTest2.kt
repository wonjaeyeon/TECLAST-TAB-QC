package com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun lcdTest2(context: Context, navController: NavController) {
//    // 밝기 범위를 지정하고, 이를 흑백 색상으로 변환합니다.
//    val brightnessLevels = listOf(0f, 0.25f, 0.5f, 0.75f, 1f)
//    val colors = brightnessLevels.map { Color(1f, 1f, 1f, it) }
//
//    var colorIndex by remember { mutableStateOf(0) }
//    val scaffoldState = rememberScaffoldState()
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "LCD Brightness Test") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = Color.White,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(colors[colorIndex])
//                .clickable {
//                    colorIndex = (colorIndex + 1) % colors.size
//                }
//        )
//    }
//}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LcdTest2(context: Context, navController: NavController) {
    // 밝기 범위를 지정하고, 이를 흑백 색상으로 변환합니다.
    val brightnessLevels = listOf(0f, 0.25f, 0.5f, 0.75f, 1f)
    val colors = brightnessLevels.map { Color(1f, 1f, 1f, it) }

    var colorIndex by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "LCD Brightness Test") },
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors[colorIndex]),
            contentAlignment = Alignment.Center
        ) {
            Text(text = colorIndex.toString(), color = Color.Black, fontSize = 40.sp)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        colorIndex = (colorIndex+1) % colors.size
                        if (colorIndex == colors.size-1) {
                            navController.popBackStack()
                        }
                    }
            )
        }
    }
}
