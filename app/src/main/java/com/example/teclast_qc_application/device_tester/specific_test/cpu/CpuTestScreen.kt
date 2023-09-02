package com.example.teclast_qc_application.device_tester.specific_test.cpu

//make a screen for cpu test
import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.checkDeviceThermalStatus
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.CpuBurningTest
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuBufferTest
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuTest1
import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
import kotlinx.coroutines.runBlocking


@RequiresApi(34)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CpuTestScreen(state: TestResultState,
                  onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavController, ){

    // Create a mutable state for battery health result
    val cpuBurningTest1Result = remember { mutableStateOf<String>("") }
    val cpuTest1Result = remember { mutableStateOf<String>("") }
    val cpuTest2Result = remember { mutableStateOf<String>("") }
    val deviceThermalState = remember { mutableStateOf<String>("") }

//    val task: suspend () -> Unit = {
//        cpuBufferTest()
//        cpuTest1()
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "CPU Test") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )

        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {
//                        val timeoutMillis = 1000L  // 1 second
//                        cpuBurningTest1Result.value = CpuBurningTest(1000, timeoutMillis).start()
                        runBlocking {
                            cpuBurningTest1Result.value = CpuBurningTest(1000L, this)
                        }
                    }
                ) {
                    Text(text = "Start CPU Burning Test")
                }
                Text(
                    text = cpuBurningTest1Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )


                Button(
                    onClick = {
                        cpuTest1Result.value = cpuTest1()
                    }
                ) {
                    Text(text = "CPU Test1 : Calculation")
                }
                Text(
                    text = cpuTest1Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                //make a button for battery test
                //make a button for battery test
                Button(onClick = {
                    cpuTest2Result.value = cpuBufferTest(state = state, onEvent = onEvent)
                }) {
                    Text(text = "CPU Test2 : Buffer")

                }

                Text(
                    text = cpuTest2Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(onClick = {
                    deviceThermalState.value = checkDeviceThermalStatus(context = context).toString()
                }) {
                    Text(text = "Device Thermal after CPU Test")

                }
                Text(
                    text = deviceThermalState.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )


            }
        }
    }
    println(cpuTest2Result.value)

}