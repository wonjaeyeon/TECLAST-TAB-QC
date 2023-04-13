package com.example.teclast_qc_application.home.read_phone_state

//@Composable
//fun requestPhoneStatePermission(onPermissionGranted: () -> Unit) {
//    val context = LocalContext.current
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            onPermissionGranted()
//        }
//    }
//
//    val hasPermission = ContextCompat.checkSelfPermission(
//        context,
//        Manifest.permission.READ_PHONE_STATE
//    ) == PackageManager.PERMISSION_GRANTED
//
//    if (hasPermission) {
//        onPermissionGranted()
//    } else {
//        permissionLauncher.launch(Manifest.permission.READ_PHONE_STATE)
//    }
//}