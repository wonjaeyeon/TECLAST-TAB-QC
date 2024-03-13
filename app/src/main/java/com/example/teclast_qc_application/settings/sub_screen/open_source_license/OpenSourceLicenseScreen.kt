package com.example.teclast_qc_application.settings.sub_screen.open_source_license

import android.content.Context
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.BuildConfig
import com.example.teclast_qc_application.R

@Composable
fun OpenSourceLicenseScreen(context: Context, navController: NavController) {

    val appVersion = BuildConfig.VERSION_NAME
    val personRepository = PersonRepository()
    val getAllData = personRepository.getAllData()
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
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
                    text = "This software includes the work that is distributed in $AllLicenseToString",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                    // Add more text properties as needed
                )
                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                Divider(
                    color = MaterialTheme.colors.onPrimary,
                    thickness = 2.dp
                )

            }
            items(getAllData.size) { index ->
                CustomItem(openSourceLicenseInfo = getAllData[index])
            }

        }
    }
}


data class OpenSourceLicenseInfo(
    val id: Int,
    val Name: String,
    val License: String,
    val link: String
)

class PersonRepository {
    fun getAllData(): List<OpenSourceLicenseInfo> {
        return listOf(
            OpenSourceLicenseInfo(
                id = 0,
                Name = "Android X",
                License = "Apache License 2.0",
                link = "https://developer.android.com/jetpack/androidx"
            ),
            OpenSourceLicenseInfo(
                id = 1,
                Name = "Google Play Services",
                License = "Apache License 2.0",
                link = "https://developers.google.com/android/guides/overview"
            ),
            OpenSourceLicenseInfo(
                id = 2,
                Name = "Kotlin Coroutines",
                License = "Apache License 2.0",
                link = "https://kotlinlang.org/docs/reference/coroutines-overview.html"
            ),
            OpenSourceLicenseInfo(
                id = 3,
                Name = "CameraX",
                License = "Apache License 2.0",
                link = "https://developer.android.com/training/camerax"
            ),
            OpenSourceLicenseInfo(
                id = 4,
                Name = "Room Database",
                License = "Apache License 2.0",
                link = "https://developer.android.com/training/data-storage/room"
            ),
            OpenSourceLicenseInfo(
                id = 5,
                Name = "Ktor",
                License = "Apache License 2.0 (Server), MIT License (Client)",
                link = "https://ktor.io/"
            ),
            OpenSourceLicenseInfo(
                id = 6,
                Name = "JUnit",
                License = "Eclipse Public License 1.0",
                link = "https://junit.org/junit4/"
            ),
            OpenSourceLicenseInfo(
                id = 7,
                Name = "Espresso",
                License = "Apache License 2.0",
                link = "https://developer.android.com/training/testing/espresso"
            )
        )
    }
}

@Composable
fun CustomItem(openSourceLicenseInfo: OpenSourceLicenseInfo) {

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
                text = openSourceLicenseInfo.link,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface
            )
        }

    }
}

