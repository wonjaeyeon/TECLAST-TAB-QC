package com.teclast_korea.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit

import com.teclast_korea.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.teclast_korea.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.*
import kotlin.system.measureTimeMillis

suspend fun CpuBurnInTest(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, timeoutMillis: Long,countNumber: Int, scope: CoroutineScope
): String {
    var result = ""

    scope.launch {
        val timeTaken = measureTimeMillis {
            withTimeoutOrNull(timeoutMillis) {
                findPrimesUpTo(countNumber)
            }
        }

        result = if (timeTaken != null && timeTaken <= timeoutMillis) {
            AddTestResult(state = state, onEvent = onEvent, "CPU BURNIN TEST", "Success", Date().toString())
            "Test : Success : $timeTaken"
        } else {
            AddTestResult(state = state, onEvent = onEvent, "CPU BURNIN TEST", "Fail", Date().toString())
            "Test : Fail : $timeoutMillis"
        }
    }.join()  // wait for the launch block to finish before proceeding

    return result
}