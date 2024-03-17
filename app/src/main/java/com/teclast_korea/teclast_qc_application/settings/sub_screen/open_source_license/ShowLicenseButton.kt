package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun ShowLicensesButton(context: Context) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.primary,
        ),
        onClick = {
        val intent = Intent(context, OssLicensesMenuActivity::class.java)
        context.startActivity(intent)
    }) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon (imageVector = Icons.Default.Description, contentDescription = "Licenses")
            Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
            Text(text = "All Open Source Licenses")
        }

    }
}