package com.teclast_korea.teclast_qc_application.device_tester.specific_test.camera.tester
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Context
//import android.content.pm.PackageManager
//import android.util.Log
//import android.view.ScaleGestureDetector
//import androidx.camera.core.Camera
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInteropFilter
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.navigation.NavController
//import com.teclast_korea.teclast_qc_application.device_tester.total_test.api_kit.FailTestNavigator
//import com.teclast_korea.teclast_qc_application.device_tester.total_test.api_kit.NavigationPopButton
//import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
//import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
//import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
//import java.util.*
//
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun CameraTest2(
//    state: TestResultState,
//    onEvent: (TestResultEvent) -> Unit,
//    context: Context, navController: NavController,
//
//    testMode: String = "",
//
//    navigateToNextTest: Boolean = false,
//    nextTestRoute: MutableList<String> = mutableListOf<String>()
//) {
//    val cameraPermission = Manifest.permission.CAMERA
//    val cameraRequestCode = 1234
//    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val cameraSelector = remember { CameraSelector.DEFAULT_FRONT_CAMERA }
//
//    var camera: Camera? = null
//
//    val previewView = PreviewView(context)
//    val currentTestItem = "Camera Test 2"
//    // val device_spec_pdf = deviceSpecReportList(context)
//
//    // For controlling zoom level with slider
//    var zoomLevel by remember { mutableStateOf(1f) }
//    val maxZoomRatio = 10f // Assuming maximum zoom ratio
//    val minZoomRatio = 1f // Assuming minimum zoom ratio
//
//    val cameraInfo = camera?.cameraInfo
//    val cameraControl = camera?.cameraControl
//
//
//
//
//    LaunchedEffect(cameraProviderFuture) {
//        if (ContextCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//            try {
//                // Unbind use cases before rebinding
//                cameraProvider.unbindAll()
//
//                val preview = Preview.Builder()
//                    .build()
//                    .also {
//                        it.setSurfaceProvider(previewView.surfaceProvider)
//                    }
//
//                camera = cameraProvider.bindToLifecycle(
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
////    val scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
////        override fun onScale(detector: ScaleGestureDetector): Boolean {
////            val zoomRatio = camera?.cameraInfo?.zoomState?.value?.zoomRatio
////            zoomRatio?.let {
////                val scale = detector.scaleFactor
////                camera?.cameraControl?.setZoomRatio(it * scale)
////            }
////            return true
////        }
////    })
//
//    val scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//        override fun onScale(detector: ScaleGestureDetector): Boolean {
//            val scale = detector.scaleFactor
//            zoomLevel = (zoomLevel * scale).coerceIn(minZoomRatio, maxZoomRatio)
//            camera?.cameraControl?.setZoomRatio(zoomLevel)
//            return true
//        }
//    })
//
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Front Camera Test") },
//                navigationIcon = {
//                    NavigationPopButton(
//                        navController = navController, testMode = testMode
//                    )
//                },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = MaterialTheme.colors.onPrimary,
//            )
//        },
//        floatingActionButton = {
//            Row(
//                modifier = Modifier.padding(16.dp).fillMaxWidth(),
//
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                FloatingActionButton(
//                    modifier = Modifier.padding(start = 16.dp),
//                    // add color to the background
//                    backgroundColor = Color(0xFF00FF00),
//
//                    onClick = { /* Handle success result */
//                        onEvent(TestResultEvent.SaveTestResult)
//                        AddTestResult(
//                            state = state,
//                            onEvent = onEvent,
//                            "Camera Test 2",
//                            "Success",
//                            Date().toString()
//                        )
//                        onEvent(TestResultEvent.SaveTestResult)
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:CameraTest2", "pastRoute: $pastRoute")
//                            Log.i("MyTag:CameraTest2", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:CameraTest2", "nextPath: $nextPath")
//                            Log.i("MyTag:CameraTest2", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = if (nextPathString.isNotEmpty()) {
//                                "$nextRoute/$nextPathString/$testMode"
//                            } else {
//                                nextRoute
//                            }
//
//                            navController.navigate(nextRouteWithArguments)
//                        }
//                        else
//                            navController.popBackStack()
//                    }) {
//                    Text("Good")
//                }
//                FloatingActionButton(
//                    backgroundColor = Color(0xFFFF0000),
//                    onClick = { /* Handle fail result */
//                        onEvent(TestResultEvent.SaveTestResult)
//                        AddTestResult(
//                            state = state,
//                            onEvent = onEvent,
//                            "Camera Test 2",
//                            "Fail",
//                            Date().toString()
//                        )
//                        onEvent(TestResultEvent.SaveTestResult)
//                        FailTestNavigator(
//                            onEvent = onEvent,
//                            testMode = testMode,
//                            navController = navController,
//                            navigateToNextTest = navigateToNextTest,
//                            nextTestRoute = nextTestRoute,
//                            currentTestItem = currentTestItem
//                        )
//                    }) {
//                    Text("Fail")
//                }
//            }
//        }
//    ) {Box{
//        AndroidView(
//            modifier = Modifier.fillMaxSize().pointerInteropFilter { event ->
//                scaleGestureDetector.onTouchEvent(event)
//                true
//            },
//            factory = { _ -> previewView }
//        )
//        Slider(
//            value = zoomLevel,
//            onValueChange = { newValue ->
//                zoomLevel = newValue.coerceIn(minZoomRatio, maxZoomRatio)
//                camera?.cameraControl?.setZoomRatio(zoomLevel)
//            },
//            valueRange = minZoomRatio..maxZoomRatio,
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//                .fillMaxWidth()
//        )
//    }}
//}