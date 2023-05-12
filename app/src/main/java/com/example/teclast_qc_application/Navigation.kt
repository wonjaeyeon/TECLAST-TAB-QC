package com.example.teclast_qc_application

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teclast_qc_application.device_tester.TesterScreen2
import com.example.teclast_qc_application.device_tester.sub_screen.audio.AudioTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.auido.tester.AudioTestT1
import com.example.teclast_qc_application.device_tester.sub_screen.auido.tester.VibrationTestT1
import com.example.teclast_qc_application.device_tester.sub_screen.battery.BatteryTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.camera.CameraTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.camera.tester.CameraTest1
import com.example.teclast_qc_application.device_tester.sub_screen.camera.tester.CameraTest2
import com.example.teclast_qc_application.device_tester.sub_screen.cpu.CpuTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.device_thermal.DeviceThermalTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.gpu.gpuTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.lcd_screen_test.LcdScreenTest
import com.example.teclast_qc_application.device_tester.sub_screen.lcd_screen_test.tester.LcdTest1
import com.example.teclast_qc_application.device_tester.sub_screen.lcd_screen_test.tester.LcdTest2
import com.example.teclast_qc_application.device_tester.sub_screen.ram.ramTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.touch_panel.TouchPanelTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.touch_panel.tester.touchPanelT1
import com.example.teclast_qc_application.device_tester.sub_screen.usb.usbTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.vibration.VibrationTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.wifi.WifiTestScreen
import com.example.teclast_qc_application.settings.SettingsScreen

@RequiresApi(34)
@Composable
fun navigationGraph(navController: NavHostController, context: MainActivity ) {

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
            SettingsScreen()
        }

        composable("cpu_test_screen") {
            CpuTestScreen(context = context, navController = navController)
        }

        composable("gpu_test_screen") {
            gpuTestScreen(context = context, navController = navController)
        }

        composable("battery_test_screen") {
            BatteryTestScreen(context = context, navController = navController)
        }

        composable("ram_test_screen") {
            ramTestScreen(context = context, navController = navController)
        }

        composable("usb_test_screen") {
            usbTestScreen(context = context, navController = navController)
        }

        composable("wifi_test_screen") {
            WifiTestScreen(context = context, navController = navController)
        }

        composable("touch_panel_test_screen") {
            TouchPanelTestScreen(context = context, navController = navController)
        }

        composable("touch_panel_test_t1_screen") {
            touchPanelT1(context = context, navController = navController)
//            { testResult ->
//                // Update the touchPanelTest1Result.value in the TouchPanelTestScreen when navigating back
//                navController.previousBackStackEntry?.savedStateHandle?.set("testResult", testResult)
//            }
        }

        composable("device_thermal_test_screen") {
            DeviceThermalTestScreen(context = context, navController = navController)
        }

        composable("lcd_screen_test_screen") {
            LcdScreenTest(context = context, navController = navController)
        }

        composable("lcd_screen_test_t1_screen") {
            LcdTest1(context = context, navController = navController)
        }

        composable("lcd_screen_test_t2_screen") {
            LcdTest2(context = context, navController = navController)
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





    }
}
