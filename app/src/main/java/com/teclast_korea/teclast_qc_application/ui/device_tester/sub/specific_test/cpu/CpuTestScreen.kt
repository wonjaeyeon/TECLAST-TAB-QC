package com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.cpu

//make a screen for cpu test
import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.cpu.tester.CpuBurnInTest
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.cpu.tester.checkDeviceThermalStatus
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.cpu.tester.cpuBufferTest
import com.teclast_korea.teclast_qc_application.ui.device_tester.sub.specific_test.cpu.tester.cpuTest1
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultEvent
import com.teclast_korea.teclast_qc_application.ui.test_result.TestResultState
import kotlinx.coroutines.runBlocking


@RequiresApi(34)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CpuTestScreen(
    state: TestResultState,
    onEvent: (TestResultEvent) -> Unit, context: Context, navController: NavController,
) {

    // Create a mutable state for battery health result
    val cpuBurnInTest1Result = remember { mutableStateOf<String>("") }
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
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                            cpuBurnInTest1Result.value = CpuBurnInTest(state = state, onEvent = onEvent,500L, 16600,this)
                        }
                    }
                ) {
                    Text(text = "CPU Burn-In Test")
                }
                Text(
                    text = cpuBurnInTest1Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )


                Button(
                    onClick = {
                        cpuTest1Result.value = cpuTest1(state = state, onEvent = onEvent)
                    }
                ) {
                    Text(text = "CPU Test1 : Calculation")
                }
                Text(
                    text = cpuTest1Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
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
                    color = MaterialTheme.colors.onPrimary,
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
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )


            }
        }
    }
    println(cpuTest2Result.value)

}