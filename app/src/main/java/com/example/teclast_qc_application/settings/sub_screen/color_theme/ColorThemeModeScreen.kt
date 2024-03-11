package com.example.teclast_qc_application.settings.sub_screen.color_theme

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ColorThemeModeScreen(context: Context, navController: NavController,darkTheme: MutableState<Boolean>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Theme Mode Settings") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Select your preferred theme mode:", style = MaterialTheme.typography.h6)

            // Example buttons for theme selection
            Button(
colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                onClick = {
            if(darkTheme.value) {
                // Set Theme Mode to Dark
                darkTheme.value = false
            } else {
                // Set Theme Mode to Light
            }
            /* Set Theme Mode to Light */ }) {
                Text("Light Theme")
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                onClick = { /* Set Theme Mode to Dark */
            if (darkTheme.value) {
                // Set Theme Mode to Dark
            } else {
                // Set Theme Mode to Light
                darkTheme.value = true}}) {
                Text("Dark Theme")
            }
        }
    }
}