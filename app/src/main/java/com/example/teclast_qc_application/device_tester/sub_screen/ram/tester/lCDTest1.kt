package com.example.teclast_qc_application.device_tester.sub_screen.ram.tester

import java.util.*

fun ramTest1(arraySize: Int = 100000): String {
    return try {
        val random = Random()
        val originalData = IntArray(arraySize)
        val verifyTestData = IntArray(arraySize)

        // Fill the array with random data
        for (i in originalData.indices) {
            originalData[i] = random.nextInt()
            verifyTestData[i] = originalData[i]
        }

        // Verify the data in the array
        for (i in originalData.indices) {
            if (originalData[i] != verifyTestData[i]) {
                return "RAM TEST: Failed"
            }
        }

        "RAM TEST: Success"
    } catch (error: Exception) {
        "Error: ${error.message}"
    }
}
