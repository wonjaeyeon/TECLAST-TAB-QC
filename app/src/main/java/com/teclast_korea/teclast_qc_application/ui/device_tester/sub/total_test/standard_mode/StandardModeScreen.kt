package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.total_test.standard_mode

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.teclast_korea.teclast_qc_application.batteryTestT1
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.cpu.tester.cpuBufferTest
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.gpu.tester.gpuTest1
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.ram.tester.ramTest1
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.total_test.standard_mode.sub_screen.StandardModeTestScreenScaffold
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable

fun StandardModeScreen(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavHostController
) {
    var progress by remember { mutableStateOf(0.0f) }
    var testsCompleted by remember { mutableStateOf(false) }

    // Initialize done and undone lists
    val done = remember { mutableStateListOf<String>() }
    val undone = remember {
        mutableStateListOf(
            "1. CPU Buffer",
            "2. CPU BURNIN",
            "3. GPU test 1",
            "4. GPU test 2",
            "5. RAM test 1",
            "6. ROM test 1",
            "7. Battery test 1"
        )
    }

    val tests = mutableListOf<String>(
        "battery_test_test_mode_screen",
        "wifi_test_test_mode_screen",
        "bluetooth_test_test_mode_screen",
        "usb_test_test_mode_screen",
        "touch_panel_test_t2_screen",
        "touch_panel_test_t4_screen",
        "physical_button_test_t1_screen",
        "lcd_screen_test_t1_screen",
        "lcd_screen_test_t2_screen",
        "gps_test_t1_screen",
        "g_sensor_test_t1_screen",
        "camera_test_t1_screen",
        "camera_test_t2_screen",
        "audio_test_t1_screen",
        "vibration_test_test_mode_screen",
        "flash_light_test_test_mode_screen",
        "standard_test_completed_screen",
        "standard_test_completed_screen" // this is just for emergency
    )

    if (!testsCompleted) {
        LaunchedEffect(key1 = testsCompleted) {
            withContext(Dispatchers.IO) {
                Log.i("FastModeScreen", "Test Started")
                onEvent(TestResultEvent.StartTest)
                Log.i("FastModeScreen", "Test DataBase is Ready")

                // Launch the tests asynchronously
                launchProcess(undone, done, progress, onEvent, state, context)

                testsCompleted = true
            }
        }
    }

    if (!testsCompleted) {
        StandardModeTestScreenScaffold(
            navController = navController,
            nextTestRoute = tests,
            progress = progress,
            done = done,
            undone = undone,
            content = {},
            onEvent = onEvent,
            context = context
        )
    } else {
        StandardModeScreen_2(
            state = state, onEvent = onEvent,
            context = context, navController = navController, nextTestRoute = tests
        )
    }
}

private suspend fun launchProcess(
    undone: MutableList<String>,
    done: MutableList<String>,
    progress: Float,
    onEvent: (TestResultEvent) -> Unit,
    state: TestResultState,
    context: Context
) {
    while (undone.isNotEmpty()) {
        val testName = undone.first()
        val result = executeTest(testName, undone, done, onEvent, state, context)
        Log.i("StandardModeScreen", "Test $testName completed with result: $result")
    }
}

private suspend fun executeTest(
    testName: String,
    undone: MutableList<String>,
    done: MutableList<String>,
    onEvent: (TestResultEvent) -> Unit,
    state: TestResultState,
    context: Context
): String {
    // Simulate the execution of the test
    delay(100L)
    val result = when (testName) {
        "1. CPU Buffer" -> cpuBufferTest(state, onEvent)
        "3. GPU test 1" -> gpuTest1(state, onEvent)
        "5. RAM test 1" -> ramTest1(state, onEvent)
        "7. Battery test 1" -> batteryTestT1(state, onEvent, context)
        else -> "Unknown Test"
    }

    // Update progress and move the item from undone to done
    done.add(testName)
    undone.remove(testName)
    onEvent(TestResultEvent.SaveTestResult)

    Log.i("StandardModeScreen", "$testName is called : $result : Percentage : ${(done.size.toFloat() / (done.size + undone.size)) * 100}%")
    return result
}