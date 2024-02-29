package com.example.teclast_qc_application

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun LogScreen(context: Context, navController: NavHostController) {

    var selectedOption = "Show Logs"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        val logText = remember { mutableStateOf(AnnotatedString("")) }
        val logTextString = remember { mutableStateOf("") }
        val scrollState = rememberScrollState()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Log Report",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Row(
            Modifier.fillMaxWidth(),
        ) {

            Button(
                //modifier = Modifier.bounceEffect(),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { logText.value = fetchAndHighlightErrorLogs() }) {


                Text(text = "Show Logs")
            }
            // make a litte space
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = MaterialTheme.colors.onPrimary),
                onClick = {}) {

                Text(text = "only error")
            }

            Button(
                //modifier = Modifier.bounceEffect(),
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                }
            ) {
                Text(text = "Bug Report")
            }

        }


        Text(
            text = logText.value,
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        )
    }
}


@Composable
fun LogVersionToggle(): String {
    val states = listOf(
        "Show All",
        ""
    )
    var selectedOption by remember {
        mutableStateOf(states[0])
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    val selectedColor = Color.Green
    val unselectedColor = Color.LightGray

    Surface(
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Row {
            states.forEach { text ->
                Text(
                    text = text,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .clickable {
                            onSelectionChange(text)
                        }
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                    color = if (text == selectedOption) {
                        selectedColor
                    } else {
                        unselectedColor
                    }
                )
            }
        }
    }
    return selectedOption
}


private fun fetchAndHighlightErrorLogs(): AnnotatedString {

    //Runtime.getRuntime().exec("logcat -c") // Clear the logcat buffer

    return try {
        //val process = Runtime.getRuntime().exec("logcat -d")
        val process = Runtime.getRuntime().exec("logcat -d")
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

        buildAnnotatedString {
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                if (line!!.contains(" E ")) { // Check if the line contains " E " which indicates an error log
                    withStyle(SpanStyle(color = Color.Red)) {
                        append(line!!)
                    }
                } else if (line!!.contains(" W ")) {
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




