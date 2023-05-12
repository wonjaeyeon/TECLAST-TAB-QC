package com.example.teclast_qc_application.device_tester.sub_screen.camera.tester

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.ScaleGestureDetector
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import java.util.*


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
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Camera Test") },
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
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = { context ->
//                PreviewView(context).apply {
//                    preview.setSurfaceProvider(surfaceProvider)
//                }
//            }
//        )
//    }
//}


// ver 2  - good, fail 버튼 추가
//@OptIn(ExperimentalComposeUiApi::class)
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
//    // zoom
//    var camera: Camera? = null
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
//    var scaleGestureDetector: ScaleGestureDetector? = null
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Camera Test") },
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
//                FloatingActionButton(onClick = { /* Handle success result */
//                navController.popBackStack()
//
//                }) {
//                    Text("Good")
//                }
//
//                FloatingActionButton(onClick = { /* Handle fail result */
//                navController.popBackStack()
//                }) {
//                    Text("Fail")
//                }
//            }
//        }
//    ) {
//        AndroidView(
//            modifier = Modifier.fillMaxSize()
//                                .pointerInteropFilter { event ->
//                    scaleGestureDetector?.onTouchEvent(event)
//                    true
//                },
//            factory = { context ->
//
//                scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//                    override fun onScale(detector: ScaleGestureDetector): Boolean {
//                        val zoomRatio = camera?.cameraInfo?.zoomState?.value?.zoomRatio
//                        zoomRatio?.let {
//                            val scale = detector.scaleFactor
//                            camera?.cameraControl?.setZoomRatio(it * scale)
//                        }
//                        return true
//                    }
//                })
//
//                PreviewView(context).apply {
//                    preview.setSurfaceProvider(surfaceProvider)
//                }
//            }
//        )
//    }
//}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CameraTest2(context: Context, navController: NavController) {
    val cameraPermission = Manifest.permission.CAMERA
    val cameraRequestCode = 1234
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraSelector = remember { CameraSelector.DEFAULT_FRONT_CAMERA }

    var camera: Camera? = null

    val previewView = PreviewView(context)


    LaunchedEffect(cameraProviderFuture) {
        if (ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview
                )
            } catch (exc: Exception) {
                Log.e("CameraTest", "Use case binding failed", exc)
            }
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(cameraPermission), cameraRequestCode)
        }
    }

    var scaleGestureDetector: ScaleGestureDetector? = null


    scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val zoomRatio = camera?.cameraInfo?.zoomState?.value?.zoomRatio
            zoomRatio?.let {
                val scale = detector.scaleFactor
                camera?.cameraControl?.setZoomRatio(it * scale)
            }
            return true
        }
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Front Camera Test") },
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
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FloatingActionButton(
                    modifier = Modifier.padding(start = 16.dp),
                    // add color to the background
                    backgroundColor = Color(0xFF00FF00),

                    onClick = { /* Handle success result */
                    navController.popBackStack()
                }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                    navController.popBackStack()
                }) {
                    Text("Fail")
                }
            }
        }
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize().pointerInteropFilter { event ->
                scaleGestureDetector?.onTouchEvent(event)
                true
            },
            factory = { _ -> previewView }
        )
    }
}