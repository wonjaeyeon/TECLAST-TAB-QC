package com.example.teclast_qc_application.device_tester
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.ui.theme.ButtonWithJYEffect

@Composable
fun TesterScreen2(context: Context, navController: NavHostController) {
    val buttons = listOf(
        Pair("CPU Test", Icons.Filled.Apps),
        Pair("GPU Test", Icons.Filled.DeveloperBoard),
        Pair("Battery Test", Icons.Filled.BatteryChargingFull),
        Pair("RAM Test", Icons.Filled.Memory),
        Pair("USB Test", Icons.Filled.Usb),
        Pair("Wifi Test", Icons.Filled.Wifi),
        Pair("Touch Panel Test", Icons.Filled.TouchApp),
        Pair("Device Thermal Test", Icons.Filled.Thermostat),
        Pair("LCD Screen Test", Icons.Filled.Tv),
        Pair("Camera Test", Icons.Filled.Camera),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
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
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = button.first,
                            style = TextStyle(fontSize = 30.sp, color = Color.White)
                        )
                    }
                }
            }
        }
    }
}