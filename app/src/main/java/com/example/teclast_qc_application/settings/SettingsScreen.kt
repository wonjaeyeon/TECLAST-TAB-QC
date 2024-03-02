package com.example.teclast_qc_application.settings

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Storage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun SettingsScreen(
    context: Context,
    navController: NavHostController,
    darkTheme: MutableState<Boolean>
) {
    val listItems = listOf(
        ListItem(
            1,
            "Color Theme Mode",
            if (darkTheme.value) "Dark Mode" else "Light Mode",
            Icons.Filled.Brush,
            "color_theme_mode_screen"
        ), // Icons.Filled.ArrowForwardIos
        ListItem(
            2,
            "Device Info DB",
            "Only User With Authority Allowed",
            Icons.Filled.Storage,
            "device_info_db_screen"
        ),
        ListItem(
            3,
            "Test Result DB(for Beta Test)",
            "Only User With Authority Allowed",
            Icons.Filled.Storage,
            "test_result_db_screen"
        ),
        ListItem(
            4,
            "Emergency Tool Kit",
            "Emergency Tool Kit",
            Icons.Filled.Construction,
            "emergency_tool_kit_screen"
        ),
        ListItem(
            5,
            "Developer Profile",
            "JaeYeon Won | woncow977@gmail.com | 010-9895-3138",
            Icons.Filled.Mail,
            "developer_profile_screen"
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Settings",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colors.onPrimary
            )
//            TextButton(
//                onClick = {
//                    navController.navigate("contract_screen") {
//                    }
//                }
//            ) {
//                Text("contract screen", color = Color.Green)
//            }
            MyList(listItems = listItems, navController = navController)


        }
    }
}

@Composable
fun MyList(listItems: List<ListItem>, navController: NavHostController) {
    LazyColumn {
        items(listItems) { item ->
            ListItem(item, navController)
        }
    }
}

@Composable
fun ListItem(item: ListItem, navController: NavHostController) {
    Button(
        onClick = {
            try {
                navController.navigate(item.navigationId)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(imageVector = item.image, contentDescription = "")

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
//            IconButton(
//                onClick = {}
//            ) {
//                Icon(imageVector = item.image, contentDescription = "")
//            }
        }
    }
}


data class ListItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image: ImageVector,
    val navigationId: String
)
