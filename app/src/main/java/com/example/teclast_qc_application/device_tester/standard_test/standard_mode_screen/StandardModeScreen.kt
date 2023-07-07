package com.example.teclast_qc_application.device_tester.standard_test.standard_mode_screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.batteryTest1
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.CpuBurningTest
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuBufferTest
import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuTest1
import com.example.teclast_qc_application.device_tester.specific_test.gpu.tester.gpu3DTest
import com.example.teclast_qc_application.device_tester.specific_test.gpu.tester.gpuTest1
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest1
import com.example.teclast_qc_application.device_tester.specific_test.ram.tester.ramTest1
import com.example.teclast_qc_application.device_tester.specific_test.rom.tester.romTest1
import com.example.teclast_qc_application.device_tester.standard_test.standard_mode_screen.sub_screen.TestScreenScaffold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StandardModeScreen(context: Context, navController: NavHostController) {
    var progress by remember { mutableStateOf(0.0f) }
    val coroutineScope = rememberCoroutineScope()
    var testsCompleted by remember { mutableStateOf(false) }
    // var done is empty list

    val done = remember { mutableStateListOf<String>() }
    val undone = remember {
        mutableStateListOf(
            "1. CPU Buffer",
            "2. CPU TEST 1",
            "3. CPU BURNING",
            "4. gpu test",
            "5. gpu test 2",
            "6. ram test 1",
            "7. rom test 1",
            "8. battery test 1",
            "9. battery test 2",
            "10. usb test 1",
            "11. usb test 2",
            "12. wifi test 1",
            "13. wifi test 2",
            "14. wifi test 3",
            "15. bluetooth test 1",
        )
    }
    // 매우 긴 nextTestRoute를 던져주고 이걸 딱딱 나눠서 보도록 하면 된다. 즉 호출 함수는 그냥 첫 함수인 거고 나머지 뒤에 따라오는 함수들은 내가 정한 nextTestRoute에 따라 따라오는 것이다.
    // nextTestRoute : "~~~~~~screen//~~~~~~~screen//~~~~~~screen//end" 이러면서 맨 앞에 처리된 라우터 새끼들은 싹 다 쳐내는 것이다. 그리고 체크해서 만약에 end면 그냥 함수 navigate.pop 써서 끝내버리면 된다.

    val tests = mutableListOf<String>(
        //"battery_test_t2_screen",
        "lcd_screen_test_t1_screen",
        "lcd_screen_test_t2_screen",
        "audio_test_t1_screen"
    )
    if (!testsCompleted) {
        LaunchedEffect(key1 = testsCompleted) {
            withContext(Dispatchers.IO) {
                Log.i("StandardModeScreen", "Test Started")
                // Cpu Test

                val cpuTestResult1 = cpuBufferTest()
                progress += 1f / (done.size + undone.size)
                done.add("1. CPU Buffer")
                undone.remove("1. CPU Buffer")

                Log.i("StandardModeScreen", "1. cpuBufferTest() is called : $cpuTestResult1 : Percentage : $progress")


                val cpuTestResult2 = cpuTest1()
                progress += 1f / (done.size + undone.size)
                done.add("2. CPU TEST 1")
                undone.remove("2. CPU TEST 1")
                Log.i("StandardModeScreen", "2. cpuTest1() is called : $cpuTestResult2 : Percentage : $progress")
                delay(100L)

                var cpuTestResult3 = ""
                progress += 1f / (done.size + undone.size)
                done.add("3. CPU BURNING")
                undone.remove("3. CPU BURNING")
                runBlocking {
                    cpuTestResult3 = CpuBurningTest(1000L, this)
                }
                Log.i("StandardModeScreen", "3. CpuBurningTest() is called : $cpuTestResult3 : Percentage : $progress")
                delay(100L)

                var gpuTestResult1 = ""
                runBlocking {
                    gpuTestResult1 = gpuTest1()
                }
                progress += 1f / (done.size + undone.size)
                done.add("4. gpu test")
                undone.remove("4. gpu test")
                Log.i("StandardModeScreen", "4. gpuTest1() is called : $gpuTestResult1 : Percentage : $progress")

                var gpuTestResult2 = ""
                runBlocking {
                    gpuTestResult2 = gpu3DTest()
                }
                progress += 1f / (done.size + undone.size)
                done.add("5. gpu test 2")
                undone.remove("5. gpu test 2")
                Log.i("StandardModeScreen", "5. gpuTest2() is called : $gpuTestResult2 : Percentage : $progress")

                var ramTestResult1 = ""
                runBlocking {
                    ramTestResult1 = ramTest1()
                }
                progress += 1f / (done.size + undone.size)
                done.add("6. ram test 1")
                undone.remove("6. ram test 1")
                Log.i("StandardModeScreen", "6. ramTest1() is called : $ramTestResult1 : Percentage : $progress")

                var romTestResult1 = ""
                runBlocking {
                    romTestResult1 = romTest1()
                }
                progress += 1f / (done.size + undone.size)
                done.add("7. rom test 1")
                undone.remove("7. rom test 1")
                Log.i("StandardModeScreen", "7. romTest1() is called : $romTestResult1 : Percentage : $progress")

                var batteryTestResult1 = ""
                batteryTestResult1 = batteryTest1(context)
                progress += 1f / (done.size + undone.size)
                done.add("8. battery test 1")
                undone.remove("8. battery test 1")
                Log.i("StandardModeScreen", "8. batteryTest1() is called : $batteryTestResult1 : Percentage : $progress")

                var batteryTestResult2 = ""



                testsCompleted = true


            }
        }
    }


    if (testsCompleted == false) {
//            LinearProgressIndicator(progress)
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text(text = "TESTING Test") },
//                    backgroundColor = MaterialTheme.colors.primaryVariant,
//                    contentColor = Color.White,
//                    navigationIcon = {
//                        IconButton(onClick = { navController.popBackStack() }) {
//                            Icon(
//                                imageVector = Icons.Filled.ArrowBack,
//                                contentDescription = "Back"
//                            )
//                        }
//                    }
//                )
//
//            }
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colors.primaryVariant)
//            ) {
//
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    LinearProgressIndicator(progress)
//
//                    Text(text = "done : ${done.joinToString(", ")}")
//                    Text(text = "undone : ${undone.joinToString(", ")}")
//
//                }
//            }
//        }
        TestScreenScaffold(navController = navController, progress = progress, done = done, undone = undone) {
        }
    }
    if (testsCompleted) {
        LcdTest1(
            context = context, navController = navController, navigateToNextTest = true, nextTestRoute = tests
        )
        //BatteryTestT2(context = context, navController = navController,  nextTestRoute = tests)
    }
}

