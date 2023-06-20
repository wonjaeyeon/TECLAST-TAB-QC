package com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
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
suspend fun CpuBurningTest(timeoutMillis: Long, scope: CoroutineScope): String {
    var result = ""

    scope.launch {
        val timeTaken = measureTimeMillis {
            withTimeoutOrNull(timeoutMillis) {
                findPrimesUpTo(50000)
            }
        }

        result = if (timeTaken != null && timeTaken <= timeoutMillis) {
            "Test : Success : $timeTaken"
        } else {
            "Test : Fail : $timeoutMillis"
        }
    }.join()  // wait for the launch block to finish before proceeding

    return result
}