package com.example.teclast_qc_application.device_tester.standard_test

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest1
import com.example.teclast_qc_application.device_tester.specific_test.lcd_screen_test.tester.LcdTest2

@Composable
fun StandardModeScreen(context: Context, navController: NavHostController){
    LcdTest1(context, navController)
    LcdTest2(context, navController)
}