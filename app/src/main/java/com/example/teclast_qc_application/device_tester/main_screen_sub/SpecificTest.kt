package com.example.teclast_qc_application.device_tester.main_screen_sub

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.ui.theme.ButtonWithJYEffect

@Composable
fun SpecificTest(context: Context, navController: NavHostController) {
    val buttons = listOf(
        Pair("CPU Test", Icons.Filled.Apps),
        Pair("GPU Test", Icons.Filled.DeveloperBoard),
        Pair("RAM Test", Icons.Filled.Memory),
        Pair("ROM Test", Icons.Filled.Storage),
        Pair("Battery Test", Icons.Filled.BatteryChargingFull),
        Pair("USB Test", Icons.Filled.Usb),
        Pair("Wifi Test", Icons.Filled.Wifi),
        Pair("Bluetooth Test", Icons.Filled.Bluetooth),
        Pair("Touch Panel Test", Icons.Filled.TouchApp),
        Pair("LCD Screen Test", Icons.Filled.Tv),
        Pair("Physical Button Test", Icons.Filled.Keyboard),
        Pair("Device Thermal Test", Icons.Filled.Thermostat),
        Pair("Camera Test", Icons.Filled.Camera),
        Pair("Audio Test", Icons.Filled.VolumeUp),
        Pair("Vibration Test", Icons.Filled.Vibration),
        Pair("Flash Light Test", Icons.Filled.BrightnessHigh),
        Pair("GPS Test", Icons.Filled.GpsFixed),
        Pair("G Sensor Test", Icons.Filled.EdgesensorHigh),
        Pair("Auto Sleep Test", Icons.Filled.ScreenLockPortrait),
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(buttons.size) { index ->
            val button = buttons[index]
            ButtonWithJYEffect(onClick = {
                navController.navigate("${button.first.lowercase().replace(" ", "_")}_screen")
            },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(140.dp),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = button.second,
                        contentDescription = button.first,
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = button.first,
                        style = TextStyle(fontSize = 30.sp, color = MaterialTheme.colors.onPrimary)
                    )
                }
            }
        }
    }
}