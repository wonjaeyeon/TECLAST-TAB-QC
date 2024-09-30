package com.teclast_korea.teclast_qc_application.ui.device_tester.total_test.api_kit

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavigationPopButton(
    navController: NavController,
    testMode: String = "NotTestMode",
    addtitionalAction: () -> Unit = {}
) {
    // Navigation Pop Button
    if (testMode == "NotTestMode") {
        IconButton(onClick = {
            addtitionalAction()
            navController.popBackStack()

        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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