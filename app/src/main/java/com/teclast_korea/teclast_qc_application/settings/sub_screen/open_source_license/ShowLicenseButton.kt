package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license

import android.content.Context
import android.content.Intent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun ShowLicensesButton(context: Context) {
    Button(onClick = {
        val intent = Intent(context, OssLicensesMenuActivity::class.java)
        context.startActivity(intent)
    }) {
        Text(text = "Show Open Source Licenses")
    }
}