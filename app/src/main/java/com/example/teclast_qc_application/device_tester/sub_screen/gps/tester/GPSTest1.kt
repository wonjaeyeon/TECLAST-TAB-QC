package com.example.teclast_qc_application.device_tester.sub_screen.gps.tester

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun GPSTestT1(context: Context, navController: NavController,) {
//    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    val isGPSEnabled = remember { mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) }
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
//                }
//            )
//        },
//        content = {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = if (isGPSEnabled.value) "GPS is enabled" else "GPS is disabled")
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = {
//                    isGPSEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//                }) {
//                    Text(text = "Refresh Status")
//                }
//            }
//        }
//    )
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GPSTestT1(context: Context, navController: NavController) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGPSEnabled = remember { mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) }
    val location = remember { mutableStateOf<Location?>(null) }
    val hasPermission = remember { mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) }

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
        if (hasPermission.value) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10f, locationListener)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "GPS Test") },
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
        }
    )
}
