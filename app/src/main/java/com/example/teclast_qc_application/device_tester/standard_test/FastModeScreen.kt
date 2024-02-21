package com.example.teclast_qc_application.device_tester.standard_test

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest1
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState


@Composable
fun FastModeScreen(state: TestResultState,
                   onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavHostController) {

    val tests = mutableListOf<String>("cpu_test_screen","battery_test_screen","lcd_screen_test_t1_screen", "lcd_screen_test_t2_screen", "audio_test_t1_screen")

    LcdTest1(
        state = state, onEvent = onEvent, context = context, navController = navController, navigateToNextTest = true, nextTestRoute = tests
    )
}
//    if (showTest2.value) {
////        LcdTest2(context = context, navController = navController, onTestComplete = {
////            // Handle test completion here, modify states as necessary
////            // For example, you could go to the next test:
////            showTest2.value = false
////            showTest1.value = true
////        })
//    }

