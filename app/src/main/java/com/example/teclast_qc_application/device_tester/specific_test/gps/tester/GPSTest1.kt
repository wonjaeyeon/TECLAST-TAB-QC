package com.example.teclast_qc_application.device_tester.specific_test.gps.tester

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.device_tester.standard_test.api_kit.FailTestNavigator
import com.example.teclast_qc_application.device_tester.standard_test.api_kit.NavigationPopButton
import com.example.teclast_qc_application.home.device_report.DeviceSpecReportList
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun GPSTestT1(
//    state: TestResultState,
//    onEvent: (TestResultEvent) -> Unit,
//    context: Context,
//    navController: NavController,
//    testMode: String = "",
//    navigateToNextTest: Boolean = false,
//    nextTestRoute: MutableList<String> = mutableListOf<String>()
//) {
//    // Check if GPS provider is enabled
//    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    // Now isGPSEnabled is a MutableState<Boolean> to dynamically update the UI.
//    val isGPSEnabled = remember { mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) }
//
//    // Permission state
//    val hasPermission = remember {
//        mutableStateOf(
//            ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        )
//    }
//
//    // Launch permission request
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted ->
//            hasPermission.value = isGranted
//        }
//    )
//
//    // Request permission if not granted
//    LaunchedEffect(key1 = true) {
//        if (!hasPermission.value) {
//            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//    }
//
//    // Current location state
//    val location = remember { mutableStateOf<Location?>(null) }
//
//    // Listen for location updates
//    LaunchedEffect(key1 = hasPermission.value) {
//        if (hasPermission.value && isGPSEnabled.value) {
//            try {
//                locationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER,
//                    0L,
//                    0f,
//                    object : LocationListener {
//                        override fun onLocationChanged(loc: Location) {
//                            location.value = loc
//                        }
//                        override fun onProviderEnabled(provider: String) {}
//                        override fun onProviderDisabled(provider: String) {}
//                        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
//                    }
//                )
//            } catch (e: SecurityException) {
//                // Handle exception if permissions are not granted; this is just a safeguard
//            }
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "GPS Test") },
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
//        content = {Box(
//            modifier = Modifier
//                .fillMaxSize(), // Respect padding from Scaffold
//            contentAlignment = Alignment.Center // Align content to the center
//
//        ){
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = if (isGPSEnabled.value) "GPS is enabled" else "GPS is disabled")
//                location.value?.let { loc ->
//                    Text(text = "Latitude: ${loc.latitude}, Longitude: ${loc.longitude}")
//                } ?: Text(text = "Location not available")
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = {
//                    isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//                }) {
//                    Text(text = "Refresh Status")
//                }
//            }
//        }},
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
//                            "GPS Test 1",
//                            "Success",
//                            Date().toString()
//                        )
//                        onEvent(TestResultEvent.SaveTestResult)
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:GPSTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:GPSTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:GPSTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:GPSTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = "${nextTestRoute[0]}"
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
//                            "GPS Test 1",
//                            "Fail",
//                            Date().toString()
//                        )
//                        onEvent(TestResultEvent.SaveTestResult)
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:GPSTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:GPSTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:GPSTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:GPSTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = "${nextTestRoute[0]}"
//                            }
//
//                            navController.navigate(nextRouteWithArguments)
//                        }
//                        else
//                            navController.popBackStack()
//                    }) {
//                    Text("Fail")
//                }
//            }
//        }
//    )
//}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GPSTestT1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    context: Context,
    navController: NavController,
    testMode: String = "",
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>(),
    locationClient: LocationClient // Assuming you have an instance passed here
) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGPSEnabled = remember { mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) }
    val location = remember { mutableStateOf<Location?>(null) }
//    val hasPermission = remember {
//        mutableStateOf(
//            ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        )
//    }
    val hasPermission = remember { context.hasLocationPermission() }
    val currentTestItem = "GPS Test 1"
    val device_spec_pdf = DeviceSpecReportList(context)

    val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(loc: Location) {
            location.value = loc
        }

        // Implement other methods as needed
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            // Called when the provider status changes. This method is called when
            // a provider is unable to fetch a location or if the provider has recently
            // become available after a period of unavailability.
        }

        override fun onProviderEnabled(provider: String) {
            // Called when the provider is enabled by the user.
            isGPSEnabled.value = true
        }

        override fun onProviderDisabled(provider: String) {
            // Called when the provider is disabled by the user. If requestLocationUpdates
            // is called on an already disabled provider, this method is called
            // immediately.
            isGPSEnabled.value = false
        }
    }

    LaunchedEffect(key1 = true) {
//        if (hasPermission.value) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10f, locationListener)
//        }
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


    LaunchedEffect(key1 = hasPermission) {
        if (hasPermission) {
            locationClient.getLocationUpdates(5000L).collect { loc ->
                location.value = loc
                // Check GPS enabled status inside collect if needed
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GPS Test 1") },
                navigationIcon = {
                    NavigationPopButton(
                        navController = navController, testMode = testMode
                    )
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },
        content = {Box(
            modifier = Modifier
                .fillMaxSize(), // Respect padding from Scaffold
            contentAlignment = Alignment.Center // Align content to the center

        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = if (isGPSEnabled.value) "GPS is enabled" else "GPS is disabled")
                location.value?.let { loc ->
                    Text(text = "Latitude: ${loc.latitude}, Longitude: ${loc.longitude}")
                } ?: Text(text = "Location not available")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                }) {
                    Text(text = "Refresh Status")
                }
            }
        }},
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
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "GPS Test 1",
                            "Success",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
                            Log.i("MyTag:GPSTest1", "pastRoute: $pastRoute")
                            Log.i("MyTag:GPSTest1", "nextTestRoute: $nextTestRoute")
                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                            val nextPath = nextTestRoute.drop(1)
                            val nextPathString = nextPath.joinToString(separator = "->")
                            Log.i("MyTag:GPSTest1", "nextPath: $nextPath")
                            Log.i("MyTag:GPSTest1", "nextPathString: $nextPathString")

                            var nextRouteWithArguments = ""
                            if (nextPathString.isNotEmpty()) {
                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
                            } else {
                                nextRouteWithArguments = "${nextTestRoute[0]}"
                            }

                            navController.navigate(nextRouteWithArguments)
                        }
                        else
                            navController.popBackStack()
                    }) {
                    Text("Good")
                }
                FloatingActionButton(
                    backgroundColor = Color(0xFFFF0000),
                    onClick = { /* Handle fail result */
                        onEvent(TestResultEvent.SaveTestResult)
                        AddTestResult(
                            state = state,
                            onEvent = onEvent,
                            "GPS Test 1",
                            "Fail",
                            Date().toString()
                        )
                        onEvent(TestResultEvent.SaveTestResult)
                        FailTestNavigator(
                            context = context,
                            onEvent = onEvent,
                            state = state,
                            navController = navController,
                            testMode = testMode,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem,
                            deviceSpec = device_spec_pdf
                        )
//                        if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
//                            val pastRoute = nextTestRoute.removeAt(0) // pastRoute = LCDTest1
//                            Log.i("MyTag:GPSTest1", "pastRoute: $pastRoute")
//                            Log.i("MyTag:GPSTest1", "nextTestRoute: $nextTestRoute")
//                            val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
//                            val nextPath = nextTestRoute.drop(1)
//                            val nextPathString = nextPath.joinToString(separator = "->")
//                            Log.i("MyTag:GPSTest1", "nextPath: $nextPath")
//                            Log.i("MyTag:GPSTest1", "nextPathString: $nextPathString")
//
//                            var nextRouteWithArguments = ""
//                            if (nextPathString.isNotEmpty()) {
//                                nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString/$testMode"
//                            } else {
//                                nextRouteWithArguments = "${nextTestRoute[0]}"
//                            }
//
//                            navController.navigate(nextRouteWithArguments)
//                        }
//                        else
//                            navController.popBackStack()
                    }) {
                    Text("Fail")
                }
            }
        }
    )
}
