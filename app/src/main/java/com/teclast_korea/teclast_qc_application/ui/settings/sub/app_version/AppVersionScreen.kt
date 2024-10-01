package com.teclast_korea.teclast_qc_application.ui.settings.sub.app_version

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.BuildConfig
import com.teclast_korea.teclast_qc_application.R

@Composable
fun AppVersionScreen(context: Context, navController: NavController) {

    val appVersion = BuildConfig.VERSION_NAME
    val personRepository = PersonRepository()
    val getAllData = personRepository.getAllData()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Version Screen") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                            text = "TAB QC APP $appVersion",
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row
                Divider(
                    color = MaterialTheme.colors.onPrimary,
                    thickness = 2.dp
                )
                Spacer(modifier = Modifier.height(16.dp)) // Adds some space after the title row

                }
                items(getAllData.size) { index ->
                    CustomItem(context = context, versionInfo = getAllData[index])
                }

            }

    }
}


data class VersionInfo(
    val id: Int,
    val Discription: String,
    val version: String,
    val link: String,
)

class PersonRepository {
    fun getAllData(): List<VersionInfo> {
        return listOf(
            VersionInfo(
                id = 0,
                Discription = "First Release",
                version = "v 1.0.0",
                link = "https://teclastkoreaqcapp.notion.site/TECLAST_QC_APP_v1-0-0-ee3b764bd7a3464981f558938da33492?pvs=4"
            ),
        )
    }
}

@Composable
fun CustomItem(context: Context, versionInfo: VersionInfo) {

    Button(
        onClick = {
            try {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(versionInfo.link)
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        },
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
    ) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Icon(imageVector = Icons.Filled.PhoneIphone, contentDescription = "", tint = MaterialTheme.colors.onPrimary)

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            Text(
                text = versionInfo.version,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = versionInfo.Discription,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
    }
}

