package com.example.teclast_qc_application.deprecated

//import android.content.Intent
//import android.location.LocationManager
//import android.location.LocationRequest
//import android.provider.Settings
//import androidx.activity.ComponentActivity
//import com.google.android.gms.location.*
//import java.lang.ref.WeakReference
//
//object LocationManager {
//
//    private lateinit var activity: WeakReference<ComponentActivity>
//    private lateinit var locationRequest: LocationRequest
//
//    private var onUpdateLocation: WeakReference<(latitude: Double, longitude: Double) -> Unit>? = null
//
//    private val locationCallback = object : LocationCallback() {
//
//        override fun onLocationAvailability(locationAvailability: LocationAvailability) {
//            super.onLocationAvailability(locationAvailability)
//            if (!locationAvailability.isLocationAvailable) {
//                activity.get()!!.let {
//                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//
//                    it.startActivity(intent)
//                }
//            } else {
//                // Location is not available
//            }
//        }
//
//        override fun onLocationResult(locationResult: LocationResult) {
//            super.onLocationResult(locationResult)
//
//            onUpdateLocation?.get()
//                ?.invoke(locationResult.lastLocation?.latitude ?: 0.0,
//                    locationResult.lastLocation?.longitude ?: 0.0)
//        }
//    }
//
//    object Builder {
//
//        fun build(): Builder{
//
//            return this
//        }
//
//        fun create(activity: ComponentActivity): LocationManager {
//            LocationManager.activity = WeakReference(activity)
//            locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).apply {
//                setMinUpdateIntervalMillis(1000)
//                setMaxUpdateAgeMillis(0)
//                setMinUpdateDistanceMeters(1.0f)
//                setGranularity(Granularity.GRANULARITY_FINE)
//            }.build()
//            return LocationManager()
//        }
//    }
//
//    private fun removeCallback(activity: Activity) {
//        LocationServices.getFusedLocationProviderClient(activity.flushLocations())
//        LocationServices.getFusedLocationProviderClient(activity).removeLocationUpdates(locationCallback)
//    }
//}