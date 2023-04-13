package com.example.teclast_qc_application

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teclast_qc_application.device_tester.TesterScreen
import com.example.teclast_qc_application.device_tester.sub_screen.battery.BatteryTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.cpu.CpuTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.gpu.GpuTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.ram.RamTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.touch_panel.TouchPanelTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.touch_panel.tester.touchPanelT1
import com.example.teclast_qc_application.device_tester.sub_screen.usb.usbTestScreen
import com.example.teclast_qc_application.device_tester.sub_screen.wifi.WifiTestScreen
import com.example.teclast_qc_application.settings.SettingsScreen

@RequiresApi(34)
@Composable
fun navigationGraph(navController: NavHostController, context: MainActivity ) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            CalendarScreen2(context = context)
        }
        composable(BottomNavItem.Test.screenRoute) {
            TesterScreen(context = context, navController = navController)

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
            GpuTestScreen(context = context, navController = navController)
        }

        composable("battery_test_screen") {
            BatteryTestScreen(context = context, navController = navController)
        }

        composable("ram_test_screen") {
            RamTestScreen(context = context, navController = navController)
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
        }





    }
}
