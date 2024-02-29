package com.example.teclast_qc_application

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.teclast_qc_application.calendar.read_phone_state.getDeviceSerialNumber_v2
import com.example.teclast_qc_application.home.device_spec.*
import com.example.teclast_qc_application.home.test_report.TestReportList
import com.example.teclast_qc_application.test_result.createReportFile
import com.example.teclast_qc_application.test_result.deleteReportFile
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent

@Composable
fun HomeScreen2(context: Context, onEvent: (TestResultEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "State Report",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colors.onPrimary

            )
            ShowDeviceSpecs2(context, onEvent = onEvent)
        }
    }
}


@Composable
fun ShowDeviceSpecs2(context: Context, onEvent: (TestResultEvent) -> Unit) {
    val tableData_DeviceSpec = listOf(
        "Serial" to getDeviceSerialNumber_v2(),
        "MAC Address" to getMac(context),
        "Brand" to Build.BRAND,
        "MODEL" to Build.MODEL,
        "ID" to Build.ID,
        "Build Number" to Build.DISPLAY,
        "Manufacturer" to Build.MANUFACTURER,
        "INCREMENTAL" to Build.VERSION.INCREMENTAL,
        //"IMEI" to getIMEI(context),
        "SDK" to Build.VERSION.SDK_INT.toString(),
        "Android Version" to Build.VERSION.RELEASE,
        "CPU Spec" to getCpuInfo(),
        "Resolution" to context.resources.displayMetrics.run { "${widthPixels}x${heightPixels}" },
        "RAM" to MemoryInfo(context),
        //"Internal Storage(without system)" to usedStorageInfo(context),
        "Internal Storage(without system)" to usedStorageInfo(context),
        "Battery Capacity" to getBatteryCapacity(context),
        "NFC" to context.packageManager.hasSystemFeature(PackageManager.FEATURE_NFC).toString(),
        "Bluetooth version" to getBluetoothVersion(),
        "FINGERPRINT" to Build.FINGERPRINT,
    )

    val tableData_StateReport = TestReportList(context, onEvent = onEvent)
    var selectedOption = "Device Specs"
    var selectedTableData = tableData_DeviceSpec
    var column2Text = "State Report"

    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    // The Column will be our table. Notice the use of the weights below
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        // Here is the Title
        Row(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(IntrinsicSize.Min)) {
            selectedOption = TriStateToggle()
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    createReportFile(context)
                    if (selectedOption == "Test Report") {
                        Toast.makeText(context, "Report Saved", Toast.LENGTH_SHORT).show()
                    }
                }) {
                Icon(
                    Icons.Rounded.Save,
                    contentDescription = "Save Report",
                    tint = MaterialTheme.colors.onSecondary
                )
            }
            if (selectedOption == "Test Report") {
                IconButton(onClick = {

                    deleteReportFile(context)
                    Toast.makeText(context, "Report Deleted", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Rounded.Delete, contentDescription = "Delete Report",
                        tint = MaterialTheme.colors.onSecondary)

                }
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Share, contentDescription = "Localized description",
                    tint = MaterialTheme.colors.onSecondary)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        if (selectedOption == "Device Specs") {
            selectedTableData = tableData_DeviceSpec
            column2Text = "Device Specs"
        } else {
            selectedTableData = tableData_StateReport
            column2Text = "Test Results"
        }

        // Here is the header
        Row(Modifier.background(Color.Gray).height(IntrinsicSize.Min)) {
            TableCell(text = "Info", weight = column1Weight)
            Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
            TableCell(text = column2Text, weight = column2Weight)
        }
        // Here are all the lines of your table.
        selectedTableData.forEach {
            val (spec, value) = it
            Row(
                Modifier
                    .fillMaxWidth().border(1.dp, Color.Black).height(IntrinsicSize.Min)
            ) {
                TableCell(text = spec, weight = column1Weight)
                Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
                TableCell(text = value, weight = column2Weight)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Transparent)
            .weight(weight)
            .fillMaxHeight() // Ensuring that TableCell fills the maximum available height
            .padding(8.dp),
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun TriStateToggle(): String {
    val states = listOf(
        "Device Specs",
        "Test Report",
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
            .wrapContentSize(),
        color = MaterialTheme.colors.secondary
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