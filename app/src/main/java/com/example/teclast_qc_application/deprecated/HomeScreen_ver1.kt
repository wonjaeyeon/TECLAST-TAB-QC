package com.example.teclast_qc_application


//@Composable
//fun HomeScreen2(context: Context) {
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colors.primary)
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .padding(horizontal = 16.dp)
//                .verticalScroll(rememberScrollState())
//        ) {
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "State Report",
//                style = MaterialTheme.typography.h6,
//                textAlign = TextAlign.Left,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//
//
//            //ShowDeviceSpecs2(context)
//
////            CreateReportButton()
////            DeleteReportButton()
//        }
//    }
//}
//
////@Composable
////fun ShowDeviceSpecs(context: Context) {
////    Column(modifier = Modifier.padding(16.dp)) {
////        Text(text = "Device Specs")
////        //get serial number
////
////        Text(text = "Serial: ${getDeviceSerialNumber(context)}")
////        Text(text = "MAC Address: ${getMac(context)}")
////        Text(text = "MODEL: ${Build.MODEL}")
////        Text(text = "ID: ${Build.ID}")
////        Text(text = "Manufacturer: ${Build.MANUFACTURER}")
////        Text(text = "Brand: ${Build.BRAND}")
////        Text(text = "Type: ${Build.TYPE}")
////        Text(text = "User: ${Build.USER}")
////        Text(text = "BASE: ${Build.VERSION_CODES.BASE}")
////        Text(text = "INCREMENTAL: ${Build.VERSION.INCREMENTAL}")
////        Text(text = "SDK: ${Build.VERSION.SDK_INT}")
////        Text(text = "BOARD: ${Build.BOARD}")
////        Text(text = "HOST: ${Build.HOST}")
////        Text(text = "FINGERPRINT: ${Build.FINGERPRINT}")
////        Text(text = "Version Code: ${Build.VERSION.RELEASE}")
////    }
////}
//
//@Composable
//fun ShowDeviceSpecs2(context: Context) {
//
//
//    val tableData_DeviceSpec = listOf(
////        "Serial" to getDeviceSerialNumber(context),
////        "MAC Address" to getMac(context),
////        "Brand" to Build.BRAND,
////        "MODEL" to Build.MODEL,
////        "ID" to Build.ID,
////        "Manufacturer" to Build.MANUFACTURER,
////        "Type" to Build.TYPE,
////        "User" to Build.USER,
////        "BASE" to Build.VERSION_CODES.BASE.toString(),
////        "INCREMENTAL" to Build.VERSION.INCREMENTAL,
////        "SDK" to Build.VERSION.SDK_INT.toString(),
////        "BOARD" to Build.BOARD,
////        "HOST" to Build.HOST,
////        "FINGERPRINT" to Build.FINGERPRINT,
////        "Version Code" to Build.VERSION.RELEASE
//        "Serial" to getDeviceSerialNumber(context),
//        "MAC Address" to getMac(context),
//        "Brand" to Build.BRAND,
//        "MODEL" to Build.MODEL,
//        "ID" to Build.ID,
//        "Manufacturer" to Build.MANUFACTURER,
//        //"Type" to Build.TYPE,
//        //"User" to Build.USER,
//        //"BASE" to Build.VERSION_CODES.BASE.toString(),
//        "INCREMENTAL" to Build.VERSION.INCREMENTAL,
//        "SDK" to Build.VERSION.SDK_INT.toString(),
//        "Android Version" to Build.VERSION.RELEASE,
//        "CPU Spec" to getCpuInfo(),
//        "Resolution" to context.resources.displayMetrics.run { "${widthPixels}x${heightPixels}" },
//        "RAM" to MemoryInfo(context),
//        //"Internal Storage(without system)" to usedStorageInfo(context),
//        "Internal Storage(without system)" to usedStorageInfo(context),
//        "Battery Capacity" to getBatteryCapacity(context),
//        "NFC" to context.packageManager.hasSystemFeature(PackageManager.FEATURE_NFC).toString(),
//        "Bluetooth version" to getBluetoothVersion(),
////        "GUID" to "DEVICE DOES NOT HAVE GUID",
////        "BOARD" to Build.BOARD,
////        "HOST" to Build.HOST,
//        "FINGERPRINT" to Build.FINGERPRINT,
//    )
//
//    val tableData_StateReport = listOf(
//        "CPU" to "PASS",
//        "RAM" to "PASS",
//        "Storage" to "PASS",
//        "Battery" to "PASS",
//        "Camera" to "PASS",
//        "Screen" to "FAIL",
//        "Speaker" to "PASS",
//        "Microphone" to "PASS",
//        "Vibrator" to "PASS",
//        "Bluetooth" to "FAIL",
//        "Wifi" to "PASS",
//        "GPS" to "PASS",
//        "Auto-Sleep" to "FAIL",
//        "Brightness" to "PASS",
//    )
//    var selectedOption = "Device Specs"
//    var selectedTableData = tableData_DeviceSpec
//    var column2Text = "State Report"
//
//    // Each cell of a column must have the same weight.
//    val column1Weight = .3f // 30%
//    val column2Weight = .7f // 70%
//    // The LazyColumn will be our table. Notice the use of the weights below
//    // The Column will be our table. Notice the use of the weights below
//    Column(Modifier.fillMaxSize().padding(16.dp)) {
//        // Here is the Title
//        Row(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(IntrinsicSize.Min)) {
////            Text(text = "Device Specs", style = MaterialTheme.typography.h6)
////            TextButton(onClick = { /*TODO*/ }) {
////                Text(text = "Device Specs", style = MaterialTheme.typography.h6, color = Color.White)
////            }
////            Divider(color = Color.White, modifier = Modifier.height(10.dp).width(1.dp))
////            TextButton(onClick = { /*TODO*/ }) {
////                Text(text = "State Report", style = MaterialTheme.typography.h6, color = Color.White)
////            }
//            selectedOption = TriStateToggle()
//            Spacer(modifier = Modifier.weight(1f))
//            IconButton(onClick = {
//                createReportFile(context)
//                if (selectedOption == "Test Report") {
//                    Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
//                }
//            }) {
//                Icon(Icons.Rounded.Save, contentDescription = "Save Report")
//            }
//            if (selectedOption == "Test Report") {
//                IconButton(onClick = { deleteReportFile(context) }) {
//                    Icon(Icons.Rounded.Delete, contentDescription = "Delete Report")
//                    Toast.makeText(context, "Report Deleted", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(Icons.Filled.Share, contentDescription = "Localized description")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//        if (selectedOption == "Device Specs") {
//            selectedTableData = tableData_DeviceSpec
//            column2Text = "Device Specs"
//        } else {
//            selectedTableData = tableData_StateReport
//            column2Text = "Test Results"
//        }
//
//
//        // Here is the header
//        Row(Modifier.background(Color.Gray).height(IntrinsicSize.Min)) {
//            TableCell(text = "Info", weight = column1Weight)
//            Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
//            TableCell(text = column2Text, weight = column2Weight)
//        }
//        // Here are all the lines of your table.
//        selectedTableData.forEach {
//            val (spec, value) = it
//            Row(
//                Modifier
//                    .fillMaxWidth().border(1.dp, Color.Black).height(IntrinsicSize.Min)
//            ) {
//                TableCell(text = spec, weight = column1Weight)
//                Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
//                TableCell(text = value, weight = column2Weight)
//            }
//        }
//    }
//}
//
//@Composable
//fun RowScope.TableCell(
//    text: String,
//    weight: Float
//) {
//    Text(
//        text = text,
//        Modifier
//            .border(1.dp, Color.Transparent)
//            .weight(weight)
//            .fillMaxHeight() // Ensuring that TableCell fills the maximum available height
//            .padding(8.dp)
//    )
//}
//
//@Composable
//fun TriStateToggle(): String {
//    val states = listOf(
//        "Device Specs",
//        "Test Report",
//    )
//    var selectedOption by remember {
//        mutableStateOf(states[0])
//    }
//    val onSelectionChange = { text: String ->
//        selectedOption = text
//    }
//    val selectedColor = Color.Green
//    val unselectedColor = Color.LightGray
//
//    Surface(
//        shape = RoundedCornerShape(24.dp),
//        elevation = 4.dp,
//        modifier = Modifier
//            .wrapContentSize()
//    ) {
//        Row {
//            states.forEach { text ->
//                Text(
//                    text = text,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(24.dp))
//                        .clickable {
//                            onSelectionChange(text)
//                        }
//                        .padding(
//                            vertical = 12.dp,
//                            horizontal = 16.dp,
//                        ),
//                    color = if (text == selectedOption) {
//                        selectedColor
//                    } else {
//                        unselectedColor
//                    }
//                )
//            }
//        }
//    }
//    return selectedOption
//}