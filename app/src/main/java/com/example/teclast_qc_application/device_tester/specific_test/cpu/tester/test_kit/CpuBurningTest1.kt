package com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit

import com.example.teclast_qc_application.test_result.test_results_db.AddTestResultV2
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.*
import kotlin.system.measureTimeMillis

//@Composable
//fun CpuBurningTest(timeoutMillis: Long): String {
//    var result by remember { mutableStateOf("") }
//
//    LaunchedEffect(timeoutMillis) {
//        val timeTaken = measureTimeMillis {
//            withTimeoutOrNull(timeoutMillis) {
//                findPrimesUpTo(50000)
//            }
//        }
//
//        result = if (timeTaken != null && timeTaken <= timeoutMillis) {
//            "Test : Success : $timeTaken"
//        } else {
//            "Test : Fail : $timeoutMillis"
//        }
//    }
//
//    return result
//}
suspend fun CpuBurningTest(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, timeoutMillis: Long, scope: CoroutineScope
): String {
    var result = ""

    scope.launch {
        val timeTaken = measureTimeMillis {
            withTimeoutOrNull(timeoutMillis) {
                findPrimesUpTo(50000)
            }
        }

        result = if (timeTaken != null && timeTaken <= timeoutMillis) {
            AddTestResultV2(state = state, onEvent = onEvent, "CPU BURNING TEST", "Success", Date().toString())
            "Test : Success : $timeTaken"
        } else {
            AddTestResultV2(state = state, onEvent = onEvent, "CPU BURNING TEST", "Fail", Date().toString())
            "Test : Fail : $timeoutMillis"
        }
    }.join()  // wait for the launch block to finish before proceeding

    return result
}