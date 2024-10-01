package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.gps.tester

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.teclast_korea.teclast_qc_application.domain.qc_result.AddTestResult
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.FailTestNavigator
import com.teclast_korea.teclast_qc_application.ui.router.api_kit.NavigationPopButton
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

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

    // Prepare the ActivityResultLauncher for permission request
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Check if all permissions are granted
        val allPermissionsGranted = permissions.entries.all { it.value }
        if (allPermissionsGranted) {
            // Permissions granted, update hasPermission and proceed with location updates
            CoroutineScope(Dispatchers.IO).launch {
                locationClient.getLocationUpdates(5000L).collect { loc ->
                    location.value = loc
                    Log.i("GPSTest1", "Location update received: Lat=${loc.latitude}, Lon=${loc.longitude}")
                    // Check GPS enabled status inside collect if needed
                }
            }
        } else {
            // Handle permission denial here
        }
    }


    var hasPermission = remember { context.hasLocationPermission() }

    val currentTestItem = "GPS Test 1"
    // val device_spec_pdf = deviceSpecReportList(context)
    val isLoading = remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        if (!hasPermission) {
            // Permissions are not granted, request them
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                )
            )
        } else {
            // Permissions are granted, start location updates
            try {
                locationClient.getLocationUpdates(5000L).collect { loc ->
                    location.value = loc
                    Log.i("GPSTest1", "Location update received: Lat=${loc.latitude}, Lon=${loc.longitude}")

                    // Check GPS enabled status inside collect if needed
                }
            } catch (e: Exception) {
                Log.e("GPSTest1", "Error requesting location updates", e)
                // 필요한 경우 여기에서 사용자에게 피드백을 제공하거나 상태를 업데이트하세요.
            }

        }


//        if (hasPermission.value) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10f, locationListener)
//        }
        //val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


    LaunchedEffect(key1 = hasPermission) {
        Log.i("GPSTest1", "hasPermission: $hasPermission")
//        if (hasPermission) {
//            Log.i("GPSTest1", "Requesting location updates")
//            locationClient.getLocationUpdates(5000L).collect { loc ->
//                location.value = loc
//                // Check GPS enabled status inside collect if needed
//            }
//        }
        if (hasPermission) {
            Log.i("GPSTest1", "Requesting location updates")
            try {
                isLoading.value = true
                locationClient.getLocationUpdates(5000L).collect { loc ->
                    location.value = loc
                    Log.i("GPSTest1", "Location update received: Lat=${loc.latitude}, Lon=${loc.longitude}")
                    // Check GPS enabled status inside collect if needed
                    isLoading.value = false
                }
            } catch (e: Exception) {
                Log.e("GPSTest1", "Error requesting location updates", e)
                isLoading.value = false
                // 필요한 경우 여기에서 사용자에게 피드백을 제공하거나 상태를 업데이트하세요.
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
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize(), // Respect padding from Scaffold
                contentAlignment = Alignment.Center // Align content to the center

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = if (isGPSEnabled.value) "GPS is enabled" else "GPS is disabled")
                    location.value?.let { loc ->
                        Text(text = "Latitude: ${loc.latitude}, Longitude: ${loc.longitude}")
                    } ?: Text(text = "Location not available")
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isLoading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            strokeWidth = 8.dp,
                            color = Color.Green
                        ) // Show loading indicator
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Button(
                        enabled = !isLoading.value,
                        onClick = {

                            Log.i("Refresh Button", "Clicked" + isLoading.value)
                            hasPermission = context.hasLocationPermission()
                            if (hasPermission) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    isLoading.value = true
                                    locationClient.getLocationUpdates(5000L).collect { loc ->
                                        location.value = loc
                                        if (loc != null) {
                                            Log.i("GPSTest1", "Location update received: Lat=${loc.latitude}, Lon=${loc.longitude}")
                                            isLoading.value = false
                                        }

                                    }

                                }
                            } else {
                                requestPermissionLauncher.launch(
                                    arrayOf(
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                    )
                                )
                            }
                            isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

                        }) {
                        Text(text = "Refresh Status")
                    }
                }
            }
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

                            var nextRouteWithArguments = if (nextPathString.isNotEmpty()) {
                                "$nextRoute/$nextPathString/$testMode"
                            } else {
                                nextRoute
                            }

                            navController.navigate(nextRouteWithArguments)
                        } else
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
                            onEvent = onEvent,
                            testMode = testMode,
                            navController = navController,
                            navigateToNextTest = navigateToNextTest,
                            nextTestRoute = nextTestRoute,
                            currentTestItem = currentTestItem
                        )

                    }) {
                    Text("Fail")
                }
            }
        }
    )
}
