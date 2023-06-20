package com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit

import java.nio.ByteBuffer
import java.util.*

fun cpuBufferTest(bufferSize: Int = 4024): String {
    return try {
        val random = Random()
        val originalData = ByteArray(bufferSize)
        random.nextBytes(originalData)

        val buffer = ByteBuffer.allocate(bufferSize)
        buffer.put(originalData)
        buffer.flip()

        val readData = ByteArray(bufferSize)
        buffer.get(readData)

        if (originalData.contentEquals(readData)) {
            "CPU BUFFER TEST: Success"
        } else {
            "CPU BUFFER TEST: Failed"
        }
    } catch (error: Exception) {
        "Error: ${error.message}"
    }
}