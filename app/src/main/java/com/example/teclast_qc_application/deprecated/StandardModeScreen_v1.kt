package com.example.teclast_qc_application.deprecated
//
//import android.content.Context
//import android.util.Log
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.CpuBurningTest
//import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuBufferTest
//import com.example.teclast_qc_application.device_tester.specific_test.cpu.tester.test_kit.cpuTest1
//import com.example.teclast_qc_application.device_tester.specific_test.gpu.tester.gpuTest1
//import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest1
//import kotlinx.coroutines.runBlocking
//
//
//@Composable
//fun StandardModeScreen(context: Context, navController: NavHostController) {
////    //make a list of tests
////    val tests = listOf("LcdTest1", "LcdTest2")
////
////    LcdTest1(context = context, navController = navController, onTestComplete = {
//////        Button(
//////            onClick = {
//////                navController.navigate("LcdTest2")
//////            }
//////        ) {
//////            Text(text = "Next Test")
//////        }
////
////    }, navigateToNextTest = true, nextTestRoute = "lcd_screen_test_t2_screen")
////
////    LcdTest2(context = context, navController = navController,runningTestMode = true, onTestComplete = {
////
////    })
//    // 매우 긴 nextTestRoute를 던져주고 이걸 딱딱 나눠서 보도록 하면 된다. 즉 호출 함수는 그냥 첫 함수인 거고 나머지 뒤에 따라오는 함수들은 내가 정한 nextTestRoute에 따라 따라오는 것이다.
//    // nextTestRoute : "~~~~~~screen//~~~~~~~screen//~~~~~~screen//end" 이러면서 맨 앞에 처리된 라우터 새끼들은 싹 다 쳐내는 것이다. 그리고 체크해서 만약에 end면 그냥 함수 navigate.pop 써서 끝내버리면 된다.
//
//    val tests = mutableListOf<String>("battery_test_screen","lcd_screen_test_t1_screen", "lcd_screen_test_t2_screen", "audio_test_t1_screen")
//
//    // Cpu Test
//    val cpuTestResult1 = cpuBufferTest()
//    Log.i("StandardModeScreen", "cpuBufferTest() is called : $cpuTestResult1")
//    val cpuTestResult2 = cpuTest1()
//    Log.i("StandardModeScreen", "cpuTest1() is called : $cpuTestResult2")
//    var cpuTestResult3 = ""
//    runBlocking {
//         cpuTestResult3 = CpuBurningTest(1000L, this)
//    }
//    Log.i("StandardModeScreen", "CpuBurningTest() is called : $cpuTestResult3")
//    var gpuTestResult1 = ""
//    runBlocking {
//        gpuTestResult1 = gpuTest1()}
//    Log.i("StandardModeScreen", "gpuTest1() is called : $gpuTestResult1")
//
//
//
//
//
//
//    LcdTest1(
//        context = context, navController = navController, navigateToNextTest = true, nextTestRoute = tests
//    )
//}
////    if (showTest2.value) {
//////        LcdTest2(context = context, navController = navController, onTestComplete = {
//////            // Handle test completion here, modify states as necessary
//////            // For example, you could go to the next test:
//////            showTest2.value = false
//////            showTest1.value = true
//////        })
////    }
//
