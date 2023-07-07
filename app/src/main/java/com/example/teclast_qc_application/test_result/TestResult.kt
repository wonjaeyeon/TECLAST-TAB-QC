package com.example.teclast_qc_application.test_result

import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

// A data class to represent test results
data class TestResult_old(val testName: String, val result: String, val timestamp: String)

// Mutable list to store test results
val testResults = mutableListOf<TestResult_old>()

// Function to add test results to the list
fun addTestResult(testName: String, result: String) {
    val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    testResults.add(TestResult_old(testName, result, timestamp))
}

// Function to generate a text report from the test results
@Throws(IOException::class)
fun generateTestReport(filename: String): String {
    val reportBuilder = StringBuilder()
    reportBuilder.append("Test Report\n")
    reportBuilder.append("Generated at: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())}\n\n")

    testResults.forEach { testResult ->
        reportBuilder.append("Test Name: ${testResult.testName}\n")
        reportBuilder.append("Result: ${testResult.result}\n")
        reportBuilder.append("Timestamp: ${testResult.timestamp}\n")
        reportBuilder.append("\n")
    }

    val report = reportBuilder.toString()

    // Save the report to a file
    File(filename).writeText(report)

    // Return the report as a string
    return report
}

//object GlobalVariables {
//    private val _testResult = mutableStateOf("")
//    val testResult: String get() = _testResult.value
//
//    fun setValue(newValue: String) {
//        _testResult.value = newValue
//    }
//}