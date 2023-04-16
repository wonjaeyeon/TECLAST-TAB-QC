package com.example.teclast_qc_application.device_tester

import android.content.Context
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.ui.theme.bounceClick


@RequiresApi(34)
@Composable

fun TesterScreen(context: Context, navController: NavHostController ) {


    val scrollState = rememberScrollState() // ScrollState에 대한 remember
    val cpuTestResult = remember { mutableStateOf<Boolean>(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp).verticalScroll(scrollState) // Column에 ScrollState 설정

        ) {
            Button(

                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp), // Adjust the padding as needed
                    

                onClick = {
                    navController.navigate("cpu_test_screen")

            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
//                            .fillMaxWidth()
//                                .padding(horizontal = 20.dp)
                ) {
                    Icon(
                        //system chipset icon
                        imageVector = Icons.Filled.Apps,
                        contentDescription = "CPU Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    //Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "CPU Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                    //Spacer(modifier = Modifier.width(30.dp)) // Add space between the icon and the text
//                    if (cpuTestResult.value) {
//
//                        Icon(
//                            //system chipset icon
//                            imageVector = Icons.Filled.CheckCircle,
//                            contentDescription = "CPU Test",
//                            tint = Color.Green,
//                            modifier = Modifier.size(40.dp)
//                        )
//                    } else {
//                        Icon(
//                            //system chipset icon
//                            imageVector = Icons.Filled.Cancel,
//                            contentDescription = "CPU Test",
//                            tint = Color.Red,
//                            modifier = Modifier.size(40.dp)
//                        )
//                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp)
                        .bounceClick(), // Adjust the padding as needed

                onClick = {
                    navController.navigate("gpu_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        //system chipset icon
                        imageVector = Icons.Filled.DeveloperBoard,
                        contentDescription = "GPU Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "GPU Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp).
                    clickable {
                        navController.navigate("battery_test_screen")
                    }, // Adjust the padding as needed

                onClick = {
                    navController.navigate("battery_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        //system chipset icon
                        imageVector = Icons.Filled.BatteryChargingFull,
                        contentDescription = "Battery Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "Battery Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }
            }

            //make a button for ram test
            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp), // Adjust the padding as needed

                onClick = {
                    navController.navigate("ram_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        //system chipset icon
                        imageVector = Icons.Filled.Memory,
                        contentDescription = "RAM Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "RAM Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }
            }

            //make a button for usb test
            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp), // Adjust the padding as needed

                onClick = {
                    navController.navigate("usb_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        //system chipset icon
                        imageVector = Icons.Filled.Usb,
                        contentDescription = "USB Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "USB Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }
            }

            //make a button for wifi test
            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp), // Adjust the padding as needed

                onClick = {
                    navController.navigate("wifi_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Wifi,
                        contentDescription = "Wifi Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "WIFI Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }
            }

            //make a button for touch pannel test
            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp), // Adjust the padding as needed

                onClick = {
                    navController.navigate("touch_panel_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.TouchApp,
                        contentDescription = "Touch Panel Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "Touch Pannel Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }


            }
            //make a button for touch pannel test
            Button(
                modifier = Modifier
                    .fillMaxWidth().
                    height(140.dp). // Adjust the padding as needed
                    padding(16.dp), // Adjust the padding as needed

                onClick = {
                    navController.navigate("device_thermal_test_screen")

                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Thermostat,
                        contentDescription = "Device Thermal Test",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add space between the icon and the text
                    Text(
                        text = "Device Thermal Test",
                        style = TextStyle(fontSize = 40.sp, color = Color.White)
                    )
                }


//
//            // Battery Test Button
//        Button(onClick = {
//            val isBatteryHealthGood = checkBatteryHealth(context)
//            batteryHealthResult.value = if (isBatteryHealthGood) {
//                "Battery health is good."
//            } else {
//                "Battery health is below the threshold."
//            }
//        }) {
//            Text(text = "Battery Test")
//        }
//
//        // Display battery health result
//        Text(
//            text = batteryHealthResult.value,
//            style = MaterialTheme.typography.body1,
//            textAlign = TextAlign.Center,
//            color = Color.White,
//            modifier = Modifier.padding(top = 16.dp)
//        )
//
//        //make a button for battery test
//        //make a button for battery test
//        Button(onClick = {
//            batteryStateofdevice.value = getBatteryState(context)
//        }) {
//            Text(text = "Charing State Test")
//
//        }
//
//        Text(
//            text = batteryStateofdevice.value,
//            style = MaterialTheme.typography.body1,
//            textAlign = TextAlign.Center,
//            color = Color.White,
//            modifier = Modifier.padding(top = 16.dp)
//        )
//
//            //make a button for cpu test
//            Button(onClick = {
//                cpuStateofdevice.value = getCurrentCpuUsage().toString()
//            }) {
//                Text(text = "CPU Test")
//
//            }
//            Text(
//                text = cpuStateofdevice.value,
//                style = MaterialTheme.typography.body1,
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                modifier = Modifier.padding(top = 16.dp)
//            )
        }

            //AnimatedButton()

//            PulsatingButton(text = "aaa", onClick = {
//                navController.navigate("cpu_test_screen")
//            })
    }
}}

//sealed class Screen(val route: String) {
//    object TesterScreen : Screen("TesterScreen")
//    object Cpu : Screen("CpuTestScreen")
//}
//
//
//@Composable
//fun TesterNav(context: Context) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screen.TesterScreen.route) {
//        composable(Screen.TesterScreen.route) {
//            TesterScreen(context = context)
//        }
//        composable(Screen.Cpu.route) {
//            CpuTestScreen(context = context)
//        }
//    }
//}
//
