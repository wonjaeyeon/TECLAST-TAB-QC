package com.example.teclast_qc_application.device_tester.standard_test.fast_mode

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.device_tester.specific_test.battery.tester.BatteryTestTestMode
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.CpuBurnInTest
import com.example.teclast_qc_application.device_tester.specific_test.gpu.tester.gpu3DTest
import com.example.teclast_qc_application.device_tester.specific_test.rom.tester.romTest1
import com.example.teclast_qc_application.device_tester.standard_test.fast_mode.sub_screen.FastModeTestScreenScaffold
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FastModeScreen_2(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavHostController
) {
    var progress by remember { mutableStateOf(4/7f) }
    var testsCompleted by remember { mutableStateOf(false) }

    // var done is empty list
    val done = remember { mutableStateListOf<String>("1. CPU Buffer", "3. GPU test", "5. RAM test 1", "8. battery test 1") }
    val undone = remember {
        mutableStateListOf(
            "2. CPU BURNIN",
            "4. GPU test 2",
            "6. ROM test 1",
        )
    }
    // 매우 긴 nextTestRoute를 던져주고 이걸 딱딱 나눠서 보도록 하면 된다. 즉 호출 함수는 그냥 첫 함수인 거고 나머지 뒤에 따라오는 함수들은 내가 정한 nextTestRoute에 따라 따라오는 것이다.
    // nextTestRoute : "~~~~~~screen//~~~~~~~screen//~~~~~~screen//end" 이러면서 맨 앞에 처리된 라우터 새끼들은 싹 다 쳐내는 것이다. 그리고 체크해서 만약에 end면 그냥 함수 navigate.pop 써서 끝내버리면 된다.

    val tests = mutableListOf<String>(
        "battery_test_test_mode_screen",
        "wifi_test_test_mode_screen",
//        "bluetooth_test_test_mode_screen",
//        "usb_test_test_mode_screen",
        "touch_panel_test_t2_screen",
        "touch_panel_test_t4_screen",
        "lcd_screen_test_t1_screen",
        "lcd_screen_test_t2_screen",
        "physical_button_test_t1_screen",
        "gps_test_t1_screen",
        "g_sensor_test_t1_screen",
        "camera_test_t1_screen",
        "camera_test_t2_screen",
        "audio_test_t1_screen",
        "vibration_test_test_mode_screen",
        "flash_light_test_test_mode_screen",
        "fast_test_completed_screen"
    )
    if (!testsCompleted) {
        LaunchedEffect(key1 = testsCompleted) {
            withContext(Dispatchers.IO) {
                Log.i("StandardModeScreen", "Test Started")
                onEvent(TestResultEvent.StartTest)
                Log.i("StandardModeScreen", "Test DataBase is Ready")

                // Cpu Test
                delay(100L)


//                val cpuTestResult2 = cpuTest1(state = state, onEvent = onEvent)
//                progress += 1f / (done.size + undone.size)
//                done.add("2. CPU TEST 1")
//                undone.remove("2. CPU TEST 1")
//                Log.i("StandardModeScreen", "2. cpuTest1() is called : $cpuTestResult2 : Percentage : $progress")
//                delay(100L)
//                onEvent(TestResultEvent.SaveTestResult)
//                delay(100L)

                var romTestResult1 = ""
                runBlocking {
                    romTestResult1 = romTest1(state = state, onEvent = onEvent)
                }
                progress += 1f / (done.size + undone.size)
                done.add("7. rom test 1")
                undone.remove("7. rom test 1")
                Log.i("StandardModeScreen", "7. romTest1() is called : $romTestResult1 : Percentage : $progress")
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)

                var cpuTestResult3 = ""
                progress += 1f / (done.size + undone.size)
                done.add("3. CPU BURNIN")
                undone.remove("3. CPU BURNIN")
                runBlocking {
                    cpuTestResult3 = CpuBurnInTest(state = state, onEvent = onEvent, 500L, 16700,this) // fail when 500L/16800
                }
                Log.i("StandardModeScreen", "3. CpuBurnInTest() is called : $cpuTestResult3 : Percentage : $progress")
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)



                var gpuTestResult2 = ""
                runBlocking {
                    gpuTestResult2 = gpu3DTest(state = state, onEvent = onEvent)
                }
                progress += 1f / (done.size + undone.size)
                done.add("5. gpu test 2")
                undone.remove("5. gpu test 2")
                Log.i("StandardModeScreen", "5. gpuTest2() is called : $gpuTestResult2 : Percentage : $progress")
                delay(100L)
                onEvent(TestResultEvent.SaveTestResult)
                delay(100L)



                testsCompleted = true
            }
        }
    }

    if (testsCompleted == false) {
        FastModeTestScreenScaffold(navController = navController, progress = progress, done = done, undone = undone) {
        }
    }
    if (testsCompleted) {
        BatteryTestTestMode(
            state = state, onEvent = onEvent,
            context = context, navController = navController, navigateToNextTest = true, nextTestRoute = tests
        )
    }
}


