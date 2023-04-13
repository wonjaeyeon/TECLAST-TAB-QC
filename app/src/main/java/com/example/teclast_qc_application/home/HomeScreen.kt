package com.example.teclast_qc_application

import android.content.Context
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun CalendarScreen2(context: Context) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.text_calendar),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                color = Color.Green,
                modifier = Modifier.padding(bottom = 16.dp)
            )



            ShowDeviceSpecs()
        }
    }
}

@Composable
fun ShowDeviceSpecs() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Device Specs")
        //get serial number

        Text(text = "MODEL: ${Build.MODEL}")
        Text(text = "ID: ${Build.ID}")
        Text(text = "Manufacturer: ${Build.MANUFACTURER}")
        Text(text = "Brand: ${Build.BRAND}")
        Text(text = "Type: ${Build.TYPE}")
        Text(text = "User: ${Build.USER}")
        Text(text = "BASE: ${Build.VERSION_CODES.BASE}")
        Text(text = "INCREMENTAL: ${Build.VERSION.INCREMENTAL}")
        Text(text = "SDK: ${Build.VERSION.SDK_INT}")
        Text(text = "BOARD: ${Build.BOARD}")
        Text(text = "HOST: ${Build.HOST}")
        Text(text = "FINGERPRINT: ${Build.FINGERPRINT}")
        Text(text = "Version Code: ${Build.VERSION.RELEASE}")
    }
}