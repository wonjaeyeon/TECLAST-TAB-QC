package com.teclast_korea.teclast_qc_application

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LogScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    var commandText by remember { mutableStateOf("") }
    val logText = remember { mutableStateOf(AnnotatedString("")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth().verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Log Report",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
            TextField(
                value = commandText,
                onValueChange = { commandText = it },
                label = { Text("Run Command") },
                modifier = Modifier.weight(1f),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Terminal, contentDescription = "Run Command")
                }
            )
            Button(
                onClick = {
                    //logText.value = executeCommand(commandText)
                    //navController.navigate("sub_log_screen/$commandText")
                    val encodedCommand = URLEncoder.encode(commandText, StandardCharsets.UTF_8.toString())
                    navController.navigate("sub_log_screen/$encodedCommand")

                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Run")
            }
        }

        // Buttons for predefined commands
        CommandButtons (
            navController = navController
        )
            //command -> logText.value = executeCommand(command), navController



        Text(text = logText.value, modifier = Modifier.padding(top = 16.dp))
    }

}

@Composable
fun CommandButtons(
    navController: NavHostController
) {
    val commands = listOf(
        CommandInfo("ps", "List processes"),
        CommandInfo("logcat -d", "Dump current logcat to console"),
        CommandInfo("procrank", "Show memory usage and rankings"),
        CommandInfo("logcat -v time -d", "Dump logcat with timestamps"),
        CommandInfo("cat /proc/cpuinfo", "Display CPU information"),
        CommandInfo("cat /proc/meminfo", "Display memory information"),
        CommandInfo("vmstat", "Report virtual memory statistics"),
        CommandInfo("logcat -b system -d", "Dump system log buffer"),
        CommandInfo("getprop", "List system properties"),
        CommandInfo("df", "Display file system space usage"),
        CommandInfo("top -n 1", "Display system processes, single snapshot"),
        CommandInfo("pm list packages", "Lists all installed packages"),
    )


    Column {
        commands.forEach { command ->
            CommandItem(commandInfo = command, navController = navController)
        }

    }
}


data class CommandInfo(
    val command: String,
    val description: String
)

@Composable
fun CommandItem(commandInfo: CommandInfo, navController: NavHostController) {
    Surface(
        color = MaterialTheme.colors.primary, // This sets the background color of the Surface
        modifier = Modifier
            .padding(8.dp) // This acts as the margin around each command item
            .fillMaxWidth()
            .clickable {
                //onCommandSelected(commandInfo.command)
                //navController.navigate("sub_log_screen/${commandInfo.command}")
                val encodedCommand = URLEncoder.encode(commandInfo.command, StandardCharsets.UTF_8.toString())
                navController.navigate("sub_log_screen/$encodedCommand")
            },
        elevation = 4.dp, // This gives the elevation effect
        shape = RoundedCornerShape(8.dp) // Optional: adds rounded corners to the Surface
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Code,
                contentDescription = "Command",
                tint = MaterialTheme.colors.onPrimary
            )
            Spacer(Modifier.width(16.dp)) // Adds space between icon and text
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                Text(
                    text = commandInfo.command,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = commandInfo.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}


fun executeCommand(command: String): AnnotatedString {
    // Similar implementation as fetchAndHighlightErrorLogs, adjusted to run any command.
    return fetchAndHighlightErrorLogs(command) // Implement this function to execute any command and process output similarly
}


private fun fetchAndHighlightErrorLogs(command: String): AnnotatedString {

    //Runtime.getRuntime().exec("logcat -c") // Clear the logcat buffer

    return try {
        val process = Runtime.getRuntime().exec(command)
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

        buildAnnotatedString {
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                if (line!!.contains(" E ") or line!!.contains(" E/")) { // Check if the line contains " E " which indicates an error log
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(line!!)
                    }
                } else if (line!!.contains(" W ") or line!!.contains(" W/")) {
                    withStyle(SpanStyle(color = Color.Yellow)) {
                        append(line!!)
                    }
                } else {
                    append(line!!)
                }

                append("\n")
            }
        }
    } catch (e: Exception) {
        buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Red)) {
                append("Error displaying logs: ${e.message}")
            }
        }
    }
}




