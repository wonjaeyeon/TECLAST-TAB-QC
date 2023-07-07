package com.example.teclast_qc_application.settings

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Storage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

val listItems = listOf(
    ListItem(1, "Color Theme Mode", "Dark Mode", Icons.Filled.Brush), // Icons.Filled.ArrowForwardIos

    ListItem(3, "Device Info DB", "Only User With Authority Allowed", Icons.Filled.Storage),
    ListItem(4, "Test Result DB(for Beta Test)", "Only User With Authority Allowed", Icons.Filled.Storage),
    ListItem(5, "Emergency Tool Kit", "Emergency Tool Kit", Icons.Filled.Construction),
    ListItem(2, "Developer Profile", "JaeYeon Won | woncow977@gmail.com | 010-9895-3138", Icons.Filled.Mail),
)

@Composable
fun SettingsScreen(context: Context, navController: NavHostController) {
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
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextButton(
                onClick = {
                    navController.navigate("contract_screen") {
                    }
                }
            ) {
                Text("contract screen", color = Color.Green)
            }
            MyList(listItems = listItems)


        }
    }
}

@Composable
fun MyList(listItems: List<ListItem>) {
    LazyColumn {
        items(listItems) { item ->
            ListItem(item)
        }
    }
}

@Composable
fun ListItem(item: ListItem) {
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
                color = MaterialTheme.colors.onSurface
            )
            Text(text = item.subtitle, style = MaterialTheme.typography.body2)
        }
//            IconButton(
//                onClick = {}
//            ) {
//                Icon(imageVector = item.image, contentDescription = "")
//            }
    }
}


data class ListItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image: ImageVector
)
