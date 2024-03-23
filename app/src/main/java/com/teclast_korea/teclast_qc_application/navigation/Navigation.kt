package com.teclast_korea.teclast_qc_application.navigation

import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.android.gms.location.LocationServices
import com.teclast_korea.teclast_qc_application.BottomNavItem
import com.teclast_korea.teclast_qc_application.HomeScreen2
import com.teclast_korea.teclast_qc_application.LogScreen
import com.teclast_korea.teclast_qc_application.MainActivity
import com.teclast_korea.teclast_qc_application.device_tester.TesterScreen2
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.audio.AudioTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.audio.tester.AudioTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.auto_sleep.AutoSleepTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.auto_sleep.tester.AutoSleepTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.battery.BatteryTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.battery.tester.BatteryTestTestMode
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.bluetooth.BluetoothTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.bluetooth.tester.BluetoothTestT2
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.bluetooth.tester.BluetoothTestTestMode
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.camera.CameraTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.camera.tester.CameraTest1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.camera.tester.CameraTest2
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.cpu.CpuTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.device_thermal.DeviceThermalTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.flash_light.FlashLightTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.flash_light.tester.FlashLightTestTestMode
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.g_sensor.GSensorTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.g_sensor.tester.GSensorTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.gps.GPSTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.gps.tester.DefaultLocationClient
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.gps.tester.GPSTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.gps.tester.LocationClient
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.gpu.GpuTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.lcd_screen_test.LcdScreenTest
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest2
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.physical_button.PhysicalButtonTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.physical_button.tester.PhysicalButtonTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.ram.RamTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.rom.RomTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.touch_panel.TouchPanelTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.touch_panel.tester.*
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.usb.tester.UsbTestEachPort
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.usb.tester.UsbTestTestMode
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.usb.usbTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.vibration.VibrationTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.vibration.tester.VibrationTestT1
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.vibration.tester.VibrationTestTestMode
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.wifi.WifiTestScreen
import com.teclast_korea.teclast_qc_application.device_tester.specific_test.wifi.tester.WifiTestTestMode
import com.teclast_korea.teclast_qc_application.device_tester.total_test.fast_mode.FastModeScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.fast_mode.sub_screen.FastTestCompletedScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.fast_mode.sub_screen.FastTestFailedScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.scspro_mode.SCSPROModeScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.scspro_mode.sub_screen.SCSPROTestCompletedScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.scspro_mode.sub_screen.SCSPROTestFailedScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.standard_mode.StandardModeScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.standard_mode.sub_screen.StandardTestCompletedScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.t_order_mode.TOrderModeScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.t_order_mode.sub_screen.TOrderTestCompletedScreen
import com.teclast_korea.teclast_qc_application.device_tester.total_test.t_order_mode.sub_screen.TOrderTestFailedScreen
import com.teclast_korea.teclast_qc_application.home.pdf_export.view_pdf.ComposePDFViewer
import com.teclast_korea.teclast_qc_application.log_reports.SubLogScreen
import com.teclast_korea.teclast_qc_application.settings.SettingsScreen
import com.teclast_korea.teclast_qc_application.settings.sub_screen.app_version.AppVersionScreen
import com.teclast_korea.teclast_qc_application.settings.sub_screen.color_theme.ColorThemeModeScreen
import com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.OpenSourceLicenseScreen
import com.teclast_korea.teclast_qc_application.settings.sub_screen.test_result.TestResultDBScreen
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlin.reflect.KFunction1

@RequiresApi(34)
@Composable
fun navigationGraph(
    navController: NavHostController,
    context: MainActivity,
    state: TestResultState,
    onEvent: KFunction1<TestResultEvent, Unit>,
    volumeUpPressed: MutableState<Boolean>,
    volumeDownPressed: MutableState<Boolean>,
    openSettings: () -> Unit,
    darkTheme: MutableState<Boolean>,
    isBottomBarVisible: MutableState<Boolean>,
    onExitApp: () -> Unit,
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
            onEvent(TestResultEvent.SaveTestResult)
            onEvent(TestResultEvent.ClearPreviousTestResults)
            onEvent(TestResultEvent.SaveTestResult)
            HomeScreen2(state = state, context = context, onEvent = onEvent, navController = navController)
        }
        composable(BottomNavItem.Test.screenRoute) {
            TesterScreen2(context = context, navController = navController)

        }
        composable(BottomNavItem.Analysis.screenRoute) {
            LogScreen(context = context, navController = navController)
        }
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen(context = context, navController = navController, darkTheme = darkTheme)
        }

        composable("cpu_test_screen") {
            CpuTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable("gpu_test_screen") {
            GpuTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable("battery_test_screen") {
            BatteryTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable(
            "battery_test_test_mode_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "notNextTest"

            if (nextTestRoute == "notNextTest") {
                BatteryTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context, navController = navController, testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "battery_test_test_mode_screen")
                ////Log.i("nextTestName2", nextTestNameList.toString())

                BatteryTestTestMode(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("ram_test_screen") {
            RamTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable("rom_test_screen") {
            RomTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable("usb_test_screen") {
            usbTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable(
            "usb_test_test_mode_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"



            if (nextTestRoute == "notNextTest") {
                UsbTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context, navController = navController, testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "usb_test_test_mode_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                UsbTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("usb_test_each_port_screen") {
            UsbTestEachPort(context = context, navController = navController)
        }

        composable("wifi_test_screen") {
            WifiTestScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable(
            "wifi_test_test_mode_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                WifiTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context, navController = navController, testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "wifi_test_test_mode_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                WifiTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("bluetooth_test_screen") {
            BluetoothTestScreen(context = context, navController = navController)
        }

        composable("bluetooth_test_t2_screen") {
            BluetoothTestT2(context = context, navController = navController)
        }

        composable(
            "bluetooth_test_test_mode_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"

            if (nextTestRoute == "notNextTest") {
                BluetoothTestTestMode(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "bluetooth_test_test_mode_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                BluetoothTestTestMode(
                    state = state, onEvent = onEvent, context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("touch_panel_test_screen") {
            TouchPanelTestScreen(context = context, navController = navController)
        }


        composable(
            "touch_panel_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                TouchPanelTest1(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "touch_panel_test_t1_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                TouchPanelTest1(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable(
            "touch_panel_test_t2_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"

            if (nextTestRoute == "notNextTest") {
                TouchPanelTest2(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "touch_panel_test_t2_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                TouchPanelTest2(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable(
            "touch_panel_test_t3_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute") ?: "notNextTest"
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                TouchPanelTest3(
                    context = context, navController = navController, testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "touch_panel_test_t3_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                TouchPanelTest3(
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable(
            "touch_panel_test_t4_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"

            if (nextTestRoute == "notNextTest") {
                TouchPanelTest4(
                    state = state,
                    onEvent = onEvent, context = context, navController = navController, testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "touch_panel_test_t4_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                TouchPanelTest4(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("touch_panel_test_t5_screen") {
            TouchPanelTest5(context = context, navController = navController)
        }

        composable("physical_button_test_screen") {
            PhysicalButtonTestScreen(context = context, navController = navController)
        }

        composable(
            "physical_button_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                PhysicalButtonTestT1(
                    state = state, onEvent = onEvent,
                    context = context, navController = navController,
                    volumeUpPressed = volumeUpPressed,
                    volumeDownPressed = volumeDownPressed,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "physical_button_test_t1_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                PhysicalButtonTestT1(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    volumeUpPressed = volumeUpPressed,
                    volumeDownPressed = volumeDownPressed,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("device_thermal_test_screen") {
            DeviceThermalTestScreen(context = context, navController = navController)
        }

        composable("lcd_screen_test_screen") {
            LcdScreenTest(context = context, navController = navController)
        }

        // argument "navigateToNextTest" is used to navigate to next test screen work perfectly
        composable(
            "lcd_screen_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"

            if (nextTestRoute == "notNextTest") {
                LcdTest1(
                    state = state, onEvent = onEvent,
                    context = context, navController = navController, testMode = testMode,
                    isBottomBarVisible = isBottomBarVisible
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "lcd_screen_test_t1_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                LcdTest1(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode,
                    isBottomBarVisible = isBottomBarVisible
                )
            }
        }

        composable(
            "lcd_screen_test_t2_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                LcdTest2(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode,
                    isBottomBarVisible = isBottomBarVisible
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "lcd_screen_test_t2_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                LcdTest2(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode,
                    isBottomBarVisible = isBottomBarVisible
                )
            }
        }

        composable("camera_test_screen") {
            CameraTestScreen(navController = navController)
        }


        composable(
            "camera_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                CameraTest1(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "camera_test_t1_screen")
                Log.i("nextTestName2_Camera1", nextTestNameList.toString())
                CameraTest1(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable(
            "camera_test_t2_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                CameraTest2(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "camera_test_t2_screen")
                Log.i("nextTestName2_Camera2", nextTestNameList.toString())
                CameraTest2(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("audio_test_screen") {
            AudioTestScreen(
                state = state,
                onEvent = onEvent,
                context = context,
                navController = navController
            ) // TODO : 이 UI 없에고 밑에와 통일 (따라서 그냥 Specific Test 들어가도 PASS FAIL 기록 가능하도록)
        }

        composable(
            "audio_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                AudioTestT1(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "audio_test_t1_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                AudioTestT1(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("vibration_test_screen") {
            VibrationTestScreen(context = context, navController = navController)
        }

        composable("vibration_test_t1_screen") {
            VibrationTestT1(
                state = state,
                onEvent = onEvent, context = context, navController = navController
            )
        }

        composable(
            "vibration_test_test_mode_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"


            if (nextTestRoute == "notNextTest") {
                VibrationTestTestMode(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "vibration_test_test_mode_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                VibrationTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("flash_light_test_screen") {
            FlashLightTestScreen(context = context, navController = navController)
        }

//        composable("flash_light_test_t1_screen") {
//            FlashLightTestT1(context = context, navController = navController) // TODO : 이 UI 없에고 밑에와 통일 (따라서 그냥 Specific Test 들어가도 PASS FAIL 기록 가능하도록)
//        }

        composable(
            "flash_light_test_test_mode_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"

            if (nextTestRoute == "notNextTest") {
                FlashLightTestTestMode(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "flash_light_test_test_mode_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                FlashLightTestTestMode(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }
        composable("gps_test_screen") {
            GPSTestScreen(context = context, navController = navController)
        }

        composable(
            "gps_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"
            val locationClient: LocationClient by lazy {
                DefaultLocationClient(
                    context = context,
                    client = LocationServices.getFusedLocationProviderClient(context)
                )
            }

            if (nextTestRoute == "notNextTest") {
                GPSTestT1(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode,
                    locationClient = locationClient
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "gps_test_t1_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                GPSTestT1(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode,
                    locationClient = locationClient
                )
            }
        }

        composable("g_sensor_test_screen") {
            GSensorTestScreen(context = context, navController = navController)
        }


        composable(
            "g_sensor_test_t1_screen/{nextTestRoute}/{testMode}", arguments = listOf(
                navArgument("nextTestRoute") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
            val testMode =
                backStackEntry.arguments?.getString("testMode") ?: "NotTestMode" // default value is "NotNextTest"

            if (nextTestRoute == "notNextTest") {
                GSensorTestT1(
                    state = state,
                    onEvent = onEvent,
                    context = context,
                    navController = navController,
                    testMode = testMode
                )
            } else {
                //split nextTestRoute to get the test name
                val nextTestName = nextTestRoute!!.split("->")
                val nextTestNameList = nextTestName!!.toMutableList()
                nextTestNameList.add(0, "g_sensor_test_t1_screen")
                //Log.i("nextTestName2", nextTestNameList.toString())
                GSensorTestT1(
                    state = state, onEvent = onEvent,
                    context = context,
                    navController = navController,
                    navigateToNextTest = true,
                    nextTestRoute = nextTestNameList,
                    testMode = testMode
                )
            }
        }

        composable("auto_sleep_test_screen") {
            AutoSleepTestScreen(context = context, navController = navController)
        }

        composable("auto_sleep_test_t1_screen") {
            AutoSleepTestT1(context = context, navController = navController)
        }

        //Standard Test
        composable("standard_mode_screen") {
            StandardModeScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

        composable("standard_test_completed_screen") {
            StandardTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }

        // this is just for emergency
        composable(
            "standard_test_completed_screen/{emergency}/{testMode}", arguments = listOf(
                navArgument("emergency") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) {
            StandardTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }

        //Fast Mode Test
        composable("fast_mode_screen") {
            FastModeScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }

//        composable(
//            "fast_mode_screen_2/{nextTestRoute}", arguments = listOf(
//                navArgument("nextTestRoute") {
//                    type = NavType.StringType
//                })
//        ) { backStackEntry ->
//            val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
//            val nextTestName = nextTestRoute!!.split("->")
//            val nextTestNameList = nextTestName!!.toMutableList()
//            //Log.i("nextTestName2", nextTestNameList.toString())
//            FastModeScreen_2(
//                state = state,
//                onEvent = onEvent,
//                context = context,
//                navController = navController,
//                nextTestRoute = nextTestNameList
//            )
//        }

        composable("fast_test_completed_screen") {
            FastTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }

        //just for emergency
        composable(
            "fast_test_completed_screen/{emergency}/{testMode}", arguments = listOf(
                navArgument("emergency") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) {
            FastTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }


        composable(
            "fast_test_fail_screen/{testMode}",
            arguments = listOf(navArgument("testMode") { type = NavType.StringType })
        ) { backStackEntry ->
            //  NOTE : 이 코드는 4번이나 반복되는 코드다. + 결과도 다르게 저장된다. 좀 희한하다.
//            val deviceSpec = deviceSpecReportList(
//                context = context
//            )
//            val File = File(getDirectory(context), "Test_Report.pdf")
//            if (File.exists()) {
//                File.delete()
//            }
//            Toast.makeText(context, "Generating Report", Toast.LENGTH_SHORT).show()
//            generatePDF(
//                context = context,
//                directory = getDirectory(context),
//                state = state,
//                onEvent = onEvent,
//                deviceSpec = deviceSpec,
//                testMode = backStackEntry.arguments?.getString("testMode") ?: "unknown",
//            )
//            Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
            FastTestFailedScreen(
                context = context,
                navController = navController,
                onEvent = onEvent,
                state = state,
                onExitApp = onExitApp,
                //deviceSpec = deviceSpec,
            )
        }


        ///////////////////////////////////////
        //TOrder Mode Test
        composable("t_order_mode_screen") {
            TOrderModeScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }


        composable("t_order_test_completed_screen") {
            TOrderTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }

        //just for emergency
        composable(
            "t_order_test_completed_screen/{emergency}/{testMode}", arguments = listOf(
                navArgument("emergency") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) {
            TOrderTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }


        composable(
            "t_order_test_fail_screen/{testMode}",
            arguments = listOf(navArgument("testMode") { type = NavType.StringType })
        ) { backStackEntry ->
            //  NOTE : 이 코드는 4번이나 반복되는 코드다.
            TOrderTestFailedScreen(
                context = context,
                navController = navController,
                onEvent = onEvent,
                state = state,
                onExitApp = onExitApp,
                //deviceSpec = deviceSpec,
            )
        }


        ///////////////////////////////////////
        //SCSPRO Mode Test
        composable("scspro_mode_screen") {
            SCSPROModeScreen(state = state, onEvent = onEvent, context = context, navController = navController)
        }


        composable("scspro_test_completed_screen") {
            SCSPROTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }

        //just for emergency
        composable(
            "scspro_test_completed_screen/{emergency}/{testMode}", arguments = listOf(
                navArgument("emergency") {
                    type = NavType.StringType
                },
                navArgument("testMode") {
                    type = NavType.StringType
                })
        ) {
            SCSPROTestCompletedScreen(
                context = context,
                state = state,
                navController = navController,
                onEvent = onEvent,
                onExitApp = onExitApp
            )
        }


        composable(
            "scspro_test_fail_screen/{testMode}",
            arguments = listOf(navArgument("testMode") { type = NavType.StringType })
        ) { backStackEntry ->
            //  NOTE : 이 코드는 4번이나 반복되는 코드다.
            SCSPROTestFailedScreen(
                context = context,
                navController = navController,
                onEvent = onEvent,
                state = state,
                onExitApp = onExitApp,
                //deviceSpec = deviceSpec,
            )
        }



        composable("test_result_db_screen") {
            onEvent(TestResultEvent.SaveTestResult)
            onEvent(TestResultEvent.ClearPreviousTestResults)
            onEvent(TestResultEvent.SaveTestResult)
            TestResultDBScreen(state = state, onEvent = onEvent, navController = navController)
        }

        composable("color_theme_mode_screen") {
            ColorThemeModeScreen(context = context, navController = navController, darkTheme = darkTheme)
        }

        composable("app_version_screen") {
            AppVersionScreen(context = context, navController = navController)
        }

        composable("open_source_license_screen") {
            OpenSourceLicenseScreen(context = context, navController = navController)
        }

        composable("pdf_view_screen") {

            ComposePDFViewer(navController = navController, context = context)
        }

        composable(
            "sub_log_screen/{command}",
            arguments = listOf(navArgument("command") { type = NavType.StringType })
        ) { backStackEntry ->
            SubLogScreen(
                context = context,
                navController = navController,
                command = backStackEntry.arguments?.getString("command") ?: ""
            )
        }
    }
}
