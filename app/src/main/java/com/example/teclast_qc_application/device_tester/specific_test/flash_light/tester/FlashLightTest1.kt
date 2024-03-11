package com.example.teclast_qc_application.device_tester.specific_test.flash_light.tester


//import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.FlashOff
//import androidx.compose.material.icons.filled.FlashOn
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun FlashLightTestT1(context: Context, navController: NavController) {
//    var isFlashOn by remember { mutableStateOf(false) }
//    val testResult = remember { mutableStateOf("test Result") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Flash Light Test T1") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = MaterialTheme.colors.onPrimary,
//            )
//        },
//        content = {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.padding(16.dp)
//            ) {
//                // Flashlight Icon that changes based on isFlashOn state
//                Icon(
//                    imageVector = if (isFlashOn) Icons.Filled.FlashOn else Icons.Filled.FlashOff,
//                    contentDescription = if (isFlashOn) "Flashlight is on" else "Flashlight is off",
//                    modifier = Modifier.size(40.dp),
//                    tint = if (isFlashOn) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
//                )
//                Spacer(modifier = Modifier.height(16.dp)) // Space between icon and button
//                Button(onClick = {
//                    isFlashOn = !isFlashOn
//                    testResult.value = toggleFlashLight(context, isFlashOn)
//                }) {
//                    Text(text = if (isFlashOn) "Turn off Flashlight" else "Turn on Flashlight")
//                }
//                Text(text = if (isFlashOn)"${testResult.value}" else "Flashlight is off")
//            }
//        }
//    )
//}
//
fun toggleFlashLight(context: Context, isFlashOn: Boolean): String {
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    var testResult = ""
    try {
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraId, isFlashOn)
        testResult = if (isFlashOn) "Flashlight is on" else "Flashlight is off"
    } catch (e: Exception) {
        e.printStackTrace()
        testResult = "Cannot find Flashlight"
    }
    return testResult

}
