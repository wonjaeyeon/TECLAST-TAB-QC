package com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit

import android.content.Context
import android.os.Build
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.teclast_qc_application.test_result.test_results_db.AddTestResult
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import java.util.*
import kotlin.system.measureTimeMillis

fun cpuTest1(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit,
    limit: Int = 50000): String {

    var isPassed = false
    var errorLine = ""
    try {
        val elapsedTime = measureTimeMillis {
            findPrimesUpTo(limit)
        }
        AddTestResult(state = state, onEvent = onEvent, "CPU TEST 1", "Success", Date().toString())
        Log.i("CPU TEST 1", "Success & ${Date()}")
    return "CPU TEST: Success (Time: ${elapsedTime}ms)"
    } catch (error: Exception) {
        errorLine = error.toString()

    }

    if (isPassed){
        AddTestResult(state = state, onEvent = onEvent, "CPU TEST 1", "Success", Date().toString())
        Log.i("CPU TEST 1", "Success & ${Date()}")
        return "CPU TEST: Success"
    } else {
        AddTestResult(state = state, onEvent = onEvent, "CPU TEST 1", "Failed", Date().toString())
        Log.i("CPU TEST 1", "Failed & with error: $errorLine & ${Date()}")
        return "CPU TEST: Failed"
    }
}

fun findPrimesUpTo(limit: Int): List<Int> {
    val primes = mutableListOf<Int>()
    for (i in 2..limit) {
        if (isPrime(i)) {
            primes.add(i)
        }
    }
    return primes
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}


@RequiresApi(Build.VERSION_CODES.S)
fun getThermalHeadroom(context: Context, testPoint: Int): Float {
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    return powerManager.getThermalHeadroom(testPoint)
}