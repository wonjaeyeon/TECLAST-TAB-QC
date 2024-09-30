package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.gps.tester

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<Location>

    //class LocationException(message: String): Exception()
}