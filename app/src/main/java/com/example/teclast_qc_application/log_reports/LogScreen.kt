package com.example.teclast_qc_application

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

@Composable
fun LogScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        val logText = remember { mutableStateOf(AnnotatedString("")) }
        val logTextString = remember { mutableStateOf("") }
        val scrollState = rememberScrollState()


        Row {
            Button(
                //modifier = Modifier.bounceEffect(),
                interactionSource = remember { MutableInteractionSource() },
                    onClick = { logText.value = fetchAndHighlightErrorLogs() }) {


                Text(text = "Show Logs")
            }
            // make a litte space
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
                onClick = {}){

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

            //ButtonAnimation(text = "aaaaa",function = fetchAndHighlightErrorLogs())

        }


        Text(
            text = logText.value,
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        )
    }
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
                }
                else if (line!!.contains(" W ")){
                    withStyle(SpanStyle(color = Color.Yellow)){
                        append(line!!)
                    }
                }
                else {
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

private fun fetchAndHighlightHardwareLogs(): AnnotatedString {
    return try {
        // Filter logcat output for hardware-related tags
        val process = Runtime.getRuntime().exec("logcat -d")
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

        buildAnnotatedString {
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                when {
                    line!!.contains("PowerManager") -> {
                        withStyle(SpanStyle(color = Color.Red)) {
                            append(line!!)
                        }
                    }
                    line!!.contains("BatteryService") -> {
                        withStyle(SpanStyle(color = Color.Green)) {
                            append(line!!)
                        }
                    }
                    line!!.contains("Wifi") -> {
                        withStyle(SpanStyle(color = Color.Blue)) {
                            append(line!!)
                        }
                    }
                    line!!.contains("Bluetooth") -> {
                        withStyle(SpanStyle(color = Color.Magenta)) {
                            append(line!!)
                        }
                    }
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

fun fetchLogs(): String {
    val logcatProcess = Runtime.getRuntime().exec(arrayOf("logcat", "-d"))
    val bufferedReader = BufferedReader(InputStreamReader(logcatProcess.inputStream))
    return bufferedReader.readText()
}



fun requestBugReport(context: Context) {
    val bugReportIntent = Intent(Intent.ACTION_BUG_REPORT)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        bugReportIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    if (bugReportIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(bugReportIntent)
    } else {
        // No handler for bug report intent
        print("nothing to show")
    }
}


@Composable
fun ButtonAnimation(
    text:String="defalt",
    function:AnnotatedString,
    animationDuration: Int = 100,
    scaleDown: Float = 0.9f
) {

    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Box(
        modifier = Modifier
            .scale(scale = scale.value)
            .background(
                color = Color(0xFF35898F),
                shape = RoundedCornerShape(size = 12f)
            )
            .clickable(interactionSource = interactionSource, indication = null) {
                coroutineScope.launch {
                    scale.animateTo(
                        scaleDown,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(animationDuration),
                    )
                }
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 36.dp, vertical = 12.dp),
            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}



