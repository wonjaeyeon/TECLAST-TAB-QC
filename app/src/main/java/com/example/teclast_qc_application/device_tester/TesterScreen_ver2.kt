package com.example.teclast_qc_application.device_tester
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.device_tester.main_screen_sub.SenarioTest
import com.example.teclast_qc_application.device_tester.main_screen_sub.SpecificTest
import com.example.teclast_qc_application.device_tester.main_screen_sub.StandardTest

@Composable
fun TesterScreen2(context: Context, navController: NavHostController) {
//    val buttons = listOf(
//        Pair("CPU Test", Icons.Filled.Apps),
//        Pair("GPU Test", Icons.Filled.DeveloperBoard),
//        Pair("Battery Test", Icons.Filled.BatteryChargingFull),
//        Pair("RAM Test", Icons.Filled.Memory),
//        Pair("USB Test", Icons.Filled.Usb),
//        Pair("Wifi Test", Icons.Filled.Wifi),
//        Pair("Bluetooth Test", Icons.Filled.Bluetooth),
//        Pair("Touch Panel Test", Icons.Filled.TouchApp),
//        Pair("Physical Button Test", Icons.Filled.Keyboard),
//        Pair("Device Thermal Test", Icons.Filled.Thermostat),
//        Pair("LCD Screen Test", Icons.Filled.Tv),
//        Pair("Camera Test", Icons.Filled.Camera),
//        Pair("Audio Test", Icons.Filled.VolumeUp),
//        Pair("Vibration Test", Icons.Filled.Vibration),
//        Pair("Flash Light Test", Icons.Filled.BrightnessHigh),
//        Pair("GPS Test", Icons.Filled.GpsFixed),
//        Pair("G Sensor Test", Icons.Filled.EdgesensorHigh),
//        Pair("Auto Sleep Test", Icons.Filled.ScreenLockPortrait),
//    )
    var selectedOption = "Standard Test"


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        selectedOption = TriStateToggle2()
        Spacer(modifier = Modifier.height(8.dp))
        if (selectedOption == "Standard Test") {
            StandardTest(context, navController)
        }
        else if (selectedOption == "Specific Test") {

            SpecificTest(context, navController)
        }
        else if (selectedOption == "Seo Test") {
            SenarioTest(context, navController)
        }
        else {
            Row(
                Modifier
                    .fillMaxWidth()


                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Not Found", modifier = Modifier.align(Alignment.CenterVertically), style = MaterialTheme.typography.h4)
            }
        }

//        LazyVerticalGrid(
//            columns = GridCells.Adaptive(200.dp),
//            contentPadding = PaddingValues(8.dp)
//        ) {
//            items(buttons.size) { index ->
//                val button = buttons[index]
//                ButtonWithJYEffect(onClick = {
//                    navController.navigate("${button.first.lowercase().replace(" ", "_")}_screen")
//                },
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//                        .height(140.dp),
//
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Icon(
//                            imageVector = button.second,
//                            contentDescription = button.first,
//                            tint = Color.White,
//                            modifier = Modifier.size(40.dp)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            text = button.first,
//                            style = TextStyle(fontSize = 30.sp, color = Color.White)
//                        )
//                    }
//                }
//            }
//        }
    }
}

@Composable
fun TriStateToggle2() : String{
    val states = listOf(
        "Standard Test",
        "Specific Test",
        //"Scenario Test"
    )
    var selectedOption by remember {
        mutableStateOf(states[0])
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    val selectedColor = Color.Green
    val unselectedColor = Color.LightGray

    Surface(
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Row {
            states.forEach { text->
                Text(
                    text = text,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .clickable {
                            onSelectionChange(text)
                        }
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                    color = if (text == selectedOption) {
                        selectedColor
                    } else {
                        unselectedColor
                    }
                )
            }
        }
    }
    return selectedOption
}