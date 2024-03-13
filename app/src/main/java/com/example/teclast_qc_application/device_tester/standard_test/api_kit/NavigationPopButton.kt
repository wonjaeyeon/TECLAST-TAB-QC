package com.example.teclast_qc_application.device_tester.standard_test.api_kit

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavigationPopButton(navController: NavController, testMode: String = "NotTestMode") {
    // Navigation Pop Button
    if (testMode == "NotTestMode") {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }
    } else if (testMode == "StandardMode") {
        // Do nothing
//        IconButton( onClick = {},
//            enabled = false
//        ){
//            Icon(
//                imageVector = Icons.AutoMirrored.Filled.Assignment,
//                contentDescription = "Back",
//            )
//        }
    } else if (testMode == "FastMode") {
        // Do nothing
//        IconButton( onClick = {},
//            enabled = false
//        ){
//            Icon(
//                imageVector = Icons.Filled.ElectricBolt,
//                contentDescription = "Back",
//            )
//        }
    } else if (testMode.lowercase().contains("torder")) {
        // Do nothing
    } else {
        // Do nothing
    }
}