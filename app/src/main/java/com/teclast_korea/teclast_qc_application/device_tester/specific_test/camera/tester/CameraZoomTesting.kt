package com.teclast_korea.teclast_qc_application.device_tester.specific_test.camera.tester

//@Composable
//fun CameraPreviewScreen() {
//    // State for camera selector (front or back)
//    val lensFacing = remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
//
//    // Prepare and bind the camera lifecycle to the current lifecycle owner
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
//    val zoomState = remember { mutableStateOf(1f) } // Default zoom ratio
//
//
//    // Gesture detection for pinch-to-zoom
//    val gestureModifier = Modifier.pointerInput(Unit) {
//        detectTransformGestures { _, pan, zoom, _ ->
//            zoomState.value *= zoom
//        }
//    }
//
//    Box{
//    AndroidView(
//        factory = { ctx ->
//            val previewView = PreviewView(ctx)
//            val executor = ContextCompat.getMainExecutor(ctx)
//            cameraProviderFuture.addListener({
//                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//                val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing.value).build()
//                val preview = Preview.Builder().build().also {
//                    it.setSurfaceProvider(previewView.surfaceProvider)
//                }
//
//                try {
//                    cameraProvider.unbindAll()
//                    val camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
//                    // Listen to zoom state changes and apply zoom
//                    val cameraControl = camera.cameraControl
//
//                    val seekBar = SeekBar(ctx)
//                    //줌 리스너 추
//                    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//                        override fun onProgressChanged(
//                            seekBar: SeekBar?,
//                            progress: Int,
//                            fromUser: Boolean
//                        ) {
//                            cameraControl.setLinearZoom(progress / 100.toFloat())
//                        }
//
//                        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//                        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//                    })
//
//
//
//                    zoomState.value.let { zoomRatio ->
//                        cameraControl.setZoomRatio(zoomRatio)
//                    }
//                } catch (e: Exception) {
//                    Log.e("CameraPreview", "Use case binding failed", e)
//                }
//            }, executor)
//            previewView
//        },
//        modifier = Modifier.fillMaxSize().then(gestureModifier) // Apply the gesture modifier
//    )
//        ZoomControl(zoomState)
//}}
//
//@Composable
//fun ZoomControl(zoomState: MutableState<Float>) {
//    // SeekBar를 Compose 내에 표시
//    AndroidView(
//        modifier = Modifier.padding(horizontal = 16.dp),
//        factory = { context ->
//            SeekBar(context).apply {
//                max = 100 // SeekBar 최대값 설정
//                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                        zoomState.value = progress / 100f // Zoom 상태 업데이트
//                    }
//
//                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//                })
//            }
//        }
//    )
//}