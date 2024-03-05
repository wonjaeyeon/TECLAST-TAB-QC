package com.example.teclast_qc_application

import android.content.Context
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
import com.example.teclast_qc_application.home.device_report.DeviceSpecReportList
import com.example.teclast_qc_application.home.pdf_export.generatePDF
import com.example.teclast_qc_application.home.pdf_export.getDirectory
import com.example.teclast_qc_application.home.test_report.TestReportList
import com.example.teclast_qc_application.test_result.test_results_db.DeleteAllTestResultDialog
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState

@Composable
fun HomeScreen2(state: TestResultState, context: Context, onEvent: (TestResultEvent) -> Unit) {


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
            ShowDeviceSpecs2(state = state,context =context, onEvent = onEvent)
        }
    }


}


@Composable
fun ShowDeviceSpecs2(state: TestResultState, context: Context, onEvent: (TestResultEvent) -> Unit) {
    val tableData_DeviceSpec = DeviceSpecReportList(context)

    val tableData_StateReport = TestReportList(state = state,context =context, onEvent = onEvent)
    var selectedOption = "Device Specs"
    var selectedTableData = tableData_DeviceSpec
    var column2Text = "State Report"

    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%

    val showDeleteAlertDialog = remember { mutableStateOf(false) }

    // The LazyColumn will be our table. Notice the use of the weights below
    // The Column will be our table. Notice the use of the weights below
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        // Here is the Title
        Row(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(IntrinsicSize.Min)) {
            selectedOption = TriStateToggle()
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    generatePDF(context, getDirectory(context))
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
                    showDeleteAlertDialog.value = true
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
        if (showDeleteAlertDialog.value) {
            DeleteAllTestResultDialog(context = context, state = state, onEvent = onEvent,  showDeleteAlertDialog = showDeleteAlertDialog)
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
        color = if(text.uppercase() == "PASS") Color.Green else if(text.uppercase() == "FAIL") Color.Red else MaterialTheme.colors.onPrimary
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