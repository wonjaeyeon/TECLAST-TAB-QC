package com.example.teclast_qc_application

import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.teclast_qc_application.device_tester.TesterScreen2
import com.example.teclast_qc_application.device_tester.specific_test.audio.AudioTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.audio.tester.AudioTestT1
import com.example.teclast_qc_application.device_tester.specific_test.auto_sleep.AutoSleepTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.auto_sleep.tester.AutoSleepTestT1
import com.example.teclast_qc_application.device_tester.specific_test.battery.BatteryTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.battery.tester.BatteryTestT2
import com.example.teclast_qc_application.device_tester.specific_test.bluetooth.BluetoothTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.bluetooth.tester.BluetoothTestT1
import com.example.teclast_qc_application.device_tester.specific_test.camera.CameraTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.camera.tester.CameraTest1
import com.example.teclast_qc_application.device_tester.specific_test.camera.tester.CameraTest2
import com.example.teclast_qc_application.device_tester.specific_test.cpu.CpuTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.device_thermal.DeviceThermalTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.flash_light.FlashLightTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.flash_light.tester.FlashLightTestT1
import com.example.teclast_qc_application.device_tester.specific_test.g_sensor.GSensorTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.g_sensor.tester.GSensorTestT1
import com.example.teclast_qc_application.device_tester.specific_test.gps.GPSTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.gps.tester.GPSTestT1
import com.example.teclast_qc_application.device_tester.specific_test.gpu.GpuTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.LcdScreenTest
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest1
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest2
import com.example.teclast_qc_application.device_tester.specific_test.physical_button.PhysicalButtonTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.physical_button.tester.PhysicalButtonTestT1
import com.example.teclast_qc_application.device_tester.specific_test.ram.RamTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.rom.RomTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.touch_panel.TouchPanelTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.touch_panel.tester.*
import com.example.teclast_qc_application.device_tester.specific_test.usb.tester.UsbTestEachPort
import com.example.teclast_qc_application.device_tester.specific_test.usb.usbTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.vibration.VibrationTestScreen
import com.example.teclast_qc_application.device_tester.specific_test.vibration.tester.VibrationTestT1
import com.example.teclast_qc_application.device_tester.specific_test.wifi.WifiTestScreen
import com.example.teclast_qc_application.device_tester.standard_test.standard_mode_screen.StandardModeScreen
import com.example.teclast_qc_application.settings.SettingsScreen
import com.example.teclast_qc_application.test_result.ContactScreen
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlin.reflect.KFunction1

@RequiresApi(34)
@Composable
fun navigationGraph(
    navController: NavHostController,
    context: MainActivity,
    state: TestResultState,
    onEvent: KFunction1<TestResultEvent, Unit>,
    volumeUpPressed : MutableState<Boolean>,
    volumeDownPressed : MutableState<Boolean>,
    openSettings :  () -> Unit,
) {

//    val navController = rememberNavController()
//    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
//
//// Listen for destination changes
//    navController.addOnDestinationChangedListener { _, destination, _ ->
//        // Check if we are returning to the desired destination
//        if (destination.route == "touch_panel_test_screen") {
//            savedStateHandle?.get<String>("testResult")?.let { testResult ->
//                // Do something with the test result
//            }
//            savedStateHandle?.remove<String>("testResult")
//        }
//    }

    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen2(context = context)
        }
        composable(BottomNavItem.Test.screenRoute) {
            //TesterScreen2(context = context, navController = navController)
            TesterScreen2(context = context, navController = navController)

        }
        composable(BottomNavItem.Analysis.screenRoute) {
            LogScreen()
        }
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen(context = context, navController = navController)
        }

        composable("cpu_test_screen") {
            CpuTestScreen(context = context, navController = navController)
        }

        composable("gpu_test_screen") {
            GpuTestScreen(context = context, navController = navController)
        }

        composable("battery_test_screen") {
            BatteryTestScreen(context = context, navController = navController)
        }

        composable("battery_test_t2_screen"){
            BatteryTestT2(context = context, navController = navController)
        }

        composable("ram_test_screen") {
            RamTestScreen(context = context, navController = navController)
        }

        composable("rom_test_screen") {
            RomTestScreen(context = context, navController = navController)
        }

        composable("usb_test_screen") {
            usbTestScreen(context = context, navController = navController)
        }

        composable("usb_test_each_port_screen") {
            UsbTestEachPort(context = context, navController = navController)
        }

        composable("wifi_test_screen") {
            WifiTestScreen(context = context, navController = navController)
        }

        composable("bluetooth_test_screen") {
            BluetoothTestScreen(context = context, navController = navController)
        }

        composable("bluetooth_test_t1_screen") {
            BluetoothTestT1(context = context, navController = navController)
        }

        composable("touch_panel_test_screen") {
            TouchPanelTestScreen(context = context, navController = navController)
        }

        composable("touch_panel_test_t1_screen") {
            TouchPanelT1(context = context, navController = navController)
//            { testResult ->
//                // Update the touchPanelTest1Result.value in the TouchPanelTestScreen when navigating back
//                navController.previousBackStackEntry?.savedStateHandle?.set("testResult", testResult)
//            }
        }

        composable("touch_panel_test_t2_screen") {
            TouchPanelT2(context = context, navController = navController)
        }

        composable("touch_panel_test_t3_screen") {
            TouchPanelT3(context = context, navController = navController)
        }

        composable("touch_panel_test_t4_screen") {
            TouchPanelT4(context = context, navController = navController)
        }

        composable("touch_panel_test_t5_screen") {
            TouchPanelT5(context = context, navController = navController)
        }

        composable("physical_button_test_screen") {
            PhysicalButtonTestScreen(context = context, navController = navController)
        }

        composable("physical_button_test_t1_screen") {
            PhysicalButtonTestT1(context = context, navController = navController, volumeUpPressed = volumeUpPressed, volumeDownPressed = volumeDownPressed)
        }

        composable("device_thermal_test_screen") {
            DeviceThermalTestScreen(context = context, navController = navController)
        }

        composable("lcd_screen_test_screen") {
            LcdScreenTest(context = context, navController = navController)
        }

        // argument "navigateToNextTest" is used to navigate to next test screen work perfectly
        composable("lcd_screen_test_t1_screen/{nextTestRoute}", arguments = listOf(navArgument("nextTestRoute") {
            type = NavType.StringType
        })) {
////            LcdTest1(context = context, navController = navController, navigateToNextTest= true, nextTestRoute = "lcd_screen_test_t2_screen")
//            argument("nextRouteInfo") {
//                defaultValue = false
//            }
//            LcdTest1(context = context, navController = navController)
            if (it.arguments?.getString("nextTestRoute") == "notNextTest") {
                LcdTest1(context = context, navController = navController
                    //, testMode = it.arguments?.getString("testMode")!!
             )
            }
            else{
                val nextTestRoute = it.arguments?.getString("nextTestRoute")
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("/")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0,"lcd_screen_test_t1_screen")
                Log.i("nextTestName2", nextTestNameList.toString())
                LcdTest1(context = context, navController = navController, nextTestRoute = nextTestNameList)
                //            LcdTest2(context = context, navController = navController)}
            }
        }

        composable("lcd_screen_test_t2_screen/{nextTestRoute}", arguments = listOf(navArgument("nextTestRoute") {
            type = NavType.StringType
        })) {
            if (it.arguments?.getString("nextTestRoute") == "notNextTest") {
                LcdTest2(context = context, navController = navController)
            }
            else{
                val nextTestRoute = it.arguments?.getString("nextTestRoute")
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("/")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0,"lcd_screen_test_t2_screen")
                Log.i("nextTestName2", nextTestNameList.toString())
                LcdTest2(context = context, navController = navController, nextTestRoute = nextTestNameList)
    //            LcdTest2(context = context, navController = navController)}
            }
        }

        composable("camera_test_screen") {
            CameraTestScreen(context = context, navController = navController)
        }

        composable("camera_test_t1_screen") {
            CameraTest1(context = context, navController = navController)
        }

        composable("camera_test_t2_screen") {
            CameraTest2(context = context, navController = navController)
        }

        composable("audio_test_screen") {
            AudioTestScreen(context = context, navController = navController)
        }

        composable("audio_test_t1_screen") {
            AudioTestT1(context = context, navController = navController)
        }

        composable("vibration_test_screen") {
            VibrationTestScreen(context = context, navController = navController)
        }

        composable("vibration_test_t1_screen") {
            VibrationTestT1(context = context, navController = navController)
        }

        composable("flash_light_test_screen") {
            FlashLightTestScreen(context = context, navController = navController)
        }

        composable("flash_light_test_t1_screen") {
            FlashLightTestT1(context = context, navController = navController)
        }

        composable("gps_test_screen") {
            GPSTestScreen(context = context, navController = navController)
        }

        composable("gps_test_t1_screen") {
            GPSTestT1(context = context, navController = navController)
        }

        composable("g_sensor_test_screen") {
            GSensorTestScreen(context = context, navController = navController)
        }

        composable("g_sensor_test_t1_screen") {
            GSensorTestT1(context = context, navController = navController)
        }

        composable("auto_sleep_test_screen") {
            AutoSleepTestScreen(context = context, navController = navController)
        }

        composable("auto_sleep_test_t1_screen") {
            AutoSleepTestT1(context = context, navController = navController)
        }


        //Standard Test
        composable("standard_mode_screen") {
            StandardModeScreen(context = context, navController = navController)
        }

        composable("contract_screen") {
            ContactScreen(state = state, onEvent = onEvent, navController = navController)
        }

    }
}
