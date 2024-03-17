package com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.BuildConfig
import com.teclast_korea.teclast_qc_application.R
import com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.copyright_list.OSLRepository
import com.teclast_korea.teclast_qc_application.settings.sub_screen.open_source_license.copyright_list.OpenSourceLicenseInfo

@Composable
fun OpenSourceLicenseScreen(context: Context, navController: NavController) {

    val appVersion = BuildConfig.VERSION_NAME
    val oslRepository = OSLRepository()
    val getAllData = oslRepository.getAllLicenseInfo()
    val getAllLicenseInfo = getAllData.map { it.License }.distinct()
    val AllLicenseToString = getAllLicenseInfo.joinToString(", ")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Open Source License Screen") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
//                    // Replace this with your app's icon using painterResource if available
                    Image(
                        painter = painterResource(R.mipmap.ic_launcher_foreground),
                        contentDescription = "App Icon",
                        modifier = Modifier.size(48.dp) // Adjust the size as needed
                    )
                    Spacer(modifier = Modifier.width(16.dp)) // Adds space between icon and text

                    Text(
                        text = "TECLAST KOREA QC APP $appVersion",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                Text(
                    textAlign = TextAlign.Start,
                    text = "This application is Copyright ⓒ Teclast Korea and Wonjaeyeon developer. All rights reserved.",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                    // Add more text properties as needed
                )
                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                Text(
                    text = "This software includes the work that is distributed in $AllLicenseToString",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                    // Add more text properties as needed
                )
                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                Text(
                    text = "The following sets forth attribution notices for third party software that may be contained in portions of this application.",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                    // Add more text properties as needed
                )
                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                Divider(
                    color = MaterialTheme.colors.onPrimary,
                    thickness = 2.dp
                )
                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                ShowLicensesButton(LocalContext.current)

//                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
//                ShowLicensesButton_2(LocalContext.current)

            }

            items(getAllData.size) { index ->
                CustomItem(context = context, openSourceLicenseInfo = getAllData[index])

            }


        }
    }
}

@Composable
fun CustomItem(context: Context,openSourceLicenseInfo: OpenSourceLicenseInfo) {
    Button(
        onClick = {

            try {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(openSourceLicenseInfo.link)
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // }

        },
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Icon(
                imageVector = Icons.Filled.CollectionsBookmark,
                contentDescription = "",
                tint = MaterialTheme.colors.onPrimary
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                Text(
                    text = openSourceLicenseInfo.Name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = openSourceLicenseInfo.License,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = openSourceLicenseInfo.copyright,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = openSourceLicenseInfo.link,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}
