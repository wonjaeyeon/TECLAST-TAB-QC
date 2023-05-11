package com.example.teclast_qc_application.device_tester.sub_screen.camera.tester

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CameraTest1(context: Context, navController: NavController) {
    val cameraPermission = Manifest.permission.CAMERA
    val cameraRequestCode = 1234
    var cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }

    val preview = remember {
        Preview.Builder()
            .build()
    }

    LaunchedEffect(cameraProviderFuture) {
        if (ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview
                )
            } catch (exc: Exception) {
                Log.e("CameraTest", "Use case binding failed", exc)
            }
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(cameraPermission), cameraRequestCode)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Camera Test") },
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
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PreviewView(context).apply {
                    preview.setSurfaceProvider(surfaceProvider)
                }
            }
        )
    }
}



//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun CameraTest1(context: Context, navController: NavController) {
//    val cameraPermission = Manifest.permission.CAMERA
//    val cameraRequestCode = 1234
//    var cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }
//
//    val preview = remember {
//        Preview.Builder()
//            .build()
//    }
//
//    LaunchedEffect(cameraProviderFuture) {
//        if (ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//            try {
//                // Unbind use cases before rebinding
//                cameraProvider.unbindAll()
//
//                // Bind use cases to camera
//                cameraProvider.bindToLifecycle(
//                    lifecycleOwner, cameraSelector, preview
//                )
//            } catch (exc: Exception) {
//                Log.e("CameraTest", "Use case binding failed", exc)
//            }
//        } else {
//            ActivityCompat.requestPermissions(context as Activity, arrayOf(cameraPermission), cameraRequestCode)
//        }
//    }
//
//    AndroidView(
//        modifier = Modifier.fillMaxSize(),
//        factory = { context ->
//            PreviewView(context).apply {
//                preview.setSurfaceProvider(surfaceProvider)
//            }
//        }
//    )
//}


