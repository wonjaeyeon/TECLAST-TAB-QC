package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license

import android.content.Context
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.copyright_list.LicenseJsonGenerator

@Composable
fun ShowLicensesButton_2(context: Context) {
    Button(onClick = {
        val generator = LicenseJsonGenerator(context)
        generator.generateJson()
    }) {
        Text(text = "Show JSON")
    }
}