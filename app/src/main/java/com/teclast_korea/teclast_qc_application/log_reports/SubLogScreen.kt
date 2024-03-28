package com.teclast_korea.teclast_qc_application.log_reports

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.executeCommand
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun SubLogScreen(
    navController: NavController,
    command: String
) {
    // URL-decode the command string
    val decodedCommand = URLDecoder.decode(command, StandardCharsets.UTF_8.toString())



    val logText = remember { mutableStateOf(AnnotatedString("")) }

    // Consider fetching and setting logText.value in a LaunchedEffect or similar
    // to ensure it's done asynchronously and recomposes when the command changes
    LaunchedEffect(decodedCommand) {
        try {
            logText.value = executeCommand(decodedCommand)
        } catch (e: Exception) {
            logText.value = AnnotatedString("Error executing command: $e")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$decodedCommand") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,

            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp)) {
            item {
                Text(text = logText.value, style = MaterialTheme.typography.body1)
            }
        }
    }
}