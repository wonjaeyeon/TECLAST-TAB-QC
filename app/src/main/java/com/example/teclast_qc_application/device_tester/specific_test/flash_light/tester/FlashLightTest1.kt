package com.example.teclast_qc_application.device_tester.specific_test.flash_light.tester

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FlashLightTestT1(context: Context, navController: NavController) {
    var isFlashOn by remember { mutableStateOf(false) }
    var testResult = remember { mutableStateOf("test Result") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flash Light Test T1") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(onClick = {
                    isFlashOn = !isFlashOn
                    testResult.value = toggleFlashLight(context, isFlashOn)
                }) {
                    Text(text = if (isFlashOn) "Turn off Flashlight" else "Turn on Flashlight")
                }
                Text("${testResult.value}")
            }
        }
    )
}

fun toggleFlashLight(context: Context, isFlashOn: Boolean): String {
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    var testResult = ""
    try {
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraId, isFlashOn)
        testResult = "Flashlight is on"
    } catch (e: Exception) {
        e.printStackTrace()
        testResult = "Cannot find Flashlight"
    }
    return testResult

}


//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun AudioTestT1(context: Context, navController: NavController) {
//    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
//    leftMediaPlayer.setVolume(1f, 0f)
//
//    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
//    rightMediaPlayer.setVolume(0f, 1f)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Audio Test") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            Row(
//                modifier = Modifier.padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                FloatingActionButton(onClick = {
//                    if(leftMediaPlayer.isPlaying){
//                        leftMediaPlayer.pause()
//                    }else{
//                        leftMediaPlayer.start()
//                    }
//                }) {
//                    Text("Left")
//                }
//
//                FloatingActionButton(onClick = {
//                    if(rightMediaPlayer.isPlaying){
//                        rightMediaPlayer.pause()
//                    }else{
//                        rightMediaPlayer.start()
//                    }
//                }) {
//                    Text("Right")
//                }
//            }
//        }
//    ) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = "Press the buttons to play sounds on the left or right speaker.")
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            leftMediaPlayer.release()
//            rightMediaPlayer.release()
//        }
//    }
//}




//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun AudioTestT1(context: Context, navController: NavController) {
//    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
//    leftMediaPlayer.setVolume(1f, 0f)
//
//    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
//    rightMediaPlayer.setVolume(0f, 1f)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Audio Test") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            Row(
//                modifier = Modifier.padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                FloatingActionButton(onClick = {
//                    leftMediaPlayer.start()
//                }) {
//                    Text("Left")
//                }
//
//                FloatingActionButton(onClick = {
//                    rightMediaPlayer.start()
//                }) {
//                    Text("Right")
//                }
//            }
//        }
//    ) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = "Press the buttons to play sounds on the left or right speaker.")
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            leftMediaPlayer.release()
//            rightMediaPlayer.release()
//        }
//    }
//}
