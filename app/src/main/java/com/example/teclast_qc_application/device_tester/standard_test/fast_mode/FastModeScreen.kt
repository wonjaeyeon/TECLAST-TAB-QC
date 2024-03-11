package com.example.teclast_qc_application.device_tester.standard_test.fast_mode

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.batteryTestT1
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuBufferTest
import com.example.teclast_qc_application.device_tester.specific_test.gpu.tester.gpuTest1
import com.example.teclast_qc_application.device_tester.specific_test.ram.tester.ramTest1
import com.example.teclast_qc_application.device_tester.standard_test.fast_mode.sub_screen.FastModeTestScreenScaffold
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FastModeScreen(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavHostController
) {
    var progress by remember { mutableStateOf(0.0f) }
    var testsCompleted by remember { mutableStateOf(false) }

    // var done is empty list
    val done = remember { mutableStateListOf<String>() }
    val undone = remember {
        mutableStateListOf(
            "1. CPU Buffer",
            "2. CPU BURNIN",
            "3. GPU test",
            "4. GPU test 2",
            "5. RAM test 1",
            "6. ROM test 1",
            "7. Battery test 1",
        )
    }

    val tests = mutableListOf<String>(
        "battery_test_test_mode_screen",
//        "wifi_test_test_mode_screen",
//        "bluetooth_test_test_mode_screen",
//        "usb_test_test_mode_screen",
//        "touch_panel_test_t2_screen",
//        "touch_panel_test_t4_screen",
//        "physical_button_test_t1_screen",
        "lcd_screen_test_t1_screen",
        "lcd_screen_test_t2_screen",
        "gps_test_t1_screen",
        "g_sensor_test_t1_screen",
        "camera_test_t1_screen",
        "camera_test_t2_screen",
        "audio_test_t1_screen",
        "vibration_test_test_mode_screen",
        "flash_light_test_test_mode_screen",
        "standard_test_completed_screen"
    )

    if (!testsCompleted) {
        LaunchedEffect(key1 = testsCompleted) {
            withContext(Dispatchers.IO) {
                Log.i("FastModeScreen", "Test Started")
                onEvent(TestResultEvent.StartTest)
                Log.i("FastModeScreen", "Test DataBase is Ready")

                // Cpu Test
                delay(100L)
                val cpuTestResult1 = cpuBufferTest(state = state, onEvent = onEvent)
                progress += 1f / (done.size + undone.size)
                done.add("1. CPU Buffer")
                undone.remove("1. CPU Buffer")

                Log.i("StandardModeScreen", "1. cpuBufferTest() is called : $cpuTestResult1 : Percentage : $progress")
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)



                try {
                    var gpuTestResult1 = ""
                    runBlocking {
                        gpuTestResult1 = gpuTest1(state = state, onEvent = onEvent)
                    }
                    progress += 1f / (done.size + undone.size)
                    done.add("4. gpu test")
                    undone.remove("4. gpu test")
                    Log.i("StandardModeScreen", "4. gpuTest1() is called : $gpuTestResult1 : Percentage : $progress")
                } catch (e: Exception) {
                    Log.e("StandardModeScreen", "Error during gpuTest1: ${e.message}")
                }
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)



                var ramTestResult1 = ""
                runBlocking {
                    ramTestResult1 = ramTest1(state = state, onEvent = onEvent)
                }
                progress += 1f / (done.size + undone.size)
                done.add("6. ram test 1")
                undone.remove("6. ram test 1")
                Log.i("StandardModeScreen", "6. ramTest1() is called : $ramTestResult1 : Percentage : $progress")
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)


                var batteryTestResult1 = ""
                batteryTestResult1 = batteryTestT1(state = state, onEvent = onEvent, context = context)
                progress += 1f / (done.size + undone.size)
                done.add("8. battery test 1")
                undone.remove("8. battery test 1")
                Log.i(
                    "StandardModeScreen",
                    "8. batteryTest1() is called : $batteryTestResult1 : Percentage : $progress"
                )
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)

                testsCompleted = true
            }
        }
    }

    if (testsCompleted == false) {
        FastModeTestScreenScaffold(navController = navController, nextTestRoute = tests, progress = progress, done = done, undone = undone, content = {}, onEvent= onEvent, state = state, context = context)
    }
    if (testsCompleted) {
        FastModeScreen_2(
            state = state, onEvent = onEvent,
            context = context, navController = navController, nextTestRoute = tests
        )
    }
}


