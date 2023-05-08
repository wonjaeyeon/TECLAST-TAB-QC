package com.example.teclast_qc_application.device_tester.sub_screen.cpu.tester.test_kit

import android.content.Context
import android.os.Build
import android.os.PowerManager
import androidx.annotation.RequiresApi
import kotlin.system.measureTimeMillis
fun cpuTest1(limit: Int = 50000): String {
    return try {
        val elapsedTime = measureTimeMillis {
            findPrimesUpTo(limit)
        }
        "CPU TEST: Success (Time: ${elapsedTime}ms)"
    } catch (error: Exception) {
        "Error: ${error.message}"
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