package com.teclast_korea.teclast_qc_application.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun createTestComposable(
    navGraphBuilder: NavGraphBuilder, // Make sure you import NavGraphBuilder from the androidx.navigation package
    baseRoute: String,
    currentRoute: String
) {
    navGraphBuilder.composable(
        route = "${baseRoute}/{nextTestRoute}/{testMode}",
        arguments = listOf(
            navArgument("nextTestRoute") {
                type = NavType.StringType
            },
            navArgument("testMode") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val nextTestRoute = backStackEntry.arguments?.getString("nextTestRoute")
        val testMode = backStackEntry.arguments?.getString("testMode") ?: "NotTestMode"


        if (currentRoute == "lcd_test_t1_test_mode") {
//                // Do something
//            } else if (currentRoute = "lcd_test_t2_test_mode") {
//                // Do something
//            } else if (currentRoute = "battery_test_t3_test_mode") {
//                // Do something
//            }

        } else {
//            val nextTestNameList = nextTestRoute!!.split("->").toMutableList().apply {
//                add(0, baseRoute)
        }

    }
}

