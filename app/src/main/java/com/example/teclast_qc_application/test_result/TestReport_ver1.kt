package com.example.teclast_qc_application.test_result

import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.File

@Composable
fun CreateReportButton() {
    val context = LocalContext.current

    Button(onClick = {
        createReportFile(context)
    }) {
        Text(text = "Create Report")
    }
}

@Composable
fun DeleteReportButton() {
    val context = LocalContext.current

    Button(onClick = {
        deleteReportFile(context)
    }) {
        Text(text = "Delete Report")
    }
}


fun createReportFile(context: Context) {
    val reportFile = File(context.getExternalFilesDir(null), "report.txt")
    if (!reportFile.exists()) {
        reportFile.createNewFile()

        // Get Android ID
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

        // Generate a random 16-character alphanumeric string
        val randomChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val randomString = List(16) { randomChars.random() }.joinToString("")

        // Create the title
        val title = "Android_ID: $androidId\nRandom string: $randomString\n"

        // Write the title to the report.txt file
        reportFile.appendText(title)
    }
}

fun deleteReportFile(context: Context) {
    val reportFile = File(context.getExternalFilesDir(null), "report.txt")
    if (reportFile.exists()) {
        val deleted = reportFile.delete()
        if (deleted) {
            Log.d("FileDeletion", "report.txt successfully deleted")
        } else {
            Log.e("FileDeletion", "Failed to delete report.txt")
        }
    } else {
        Log.d("FileDeletion", "report.txt does not exist")
    }
}