package com.example.teclast_qc_application.device_tester.specific_test.physical_button.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun PhysicalButtonTestT1(
    context: Context,
    navController: NavController,
    volumeUpPressed: MutableState<Boolean>,
    volumeDownPressed: MutableState<Boolean>,
    runningTestMode: Boolean = false,
    testMode: String = "None",
    onTestComplete: () -> Unit = {},
    navigateToNextTest: Boolean = false,
    nextTestRoute: MutableList<String> = mutableListOf<String>()
) {

    // Observe volumeUpPressed and volumeDownPressed.
    // If either is true, navigate to the previous screen.
    LaunchedEffect(volumeUpPressed.value, volumeDownPressed.value) {
        if (volumeUpPressed.value && volumeDownPressed.value) {
            // navController.popBackStack()
            if (navigateToNextTest && nextTestRoute.isNotEmpty()) {
                val pastRoute = nextTestRoute.removeAt(0)
                Log.i("MyTag:PhysicalButtonTestT1", "pastRoute: $pastRoute")
                Log.i("MyTag:PhysicalButtonTestT1", "nextTestRoute: $nextTestRoute")
                val nextRoute = nextTestRoute[0] // nextRoute = LCDTest2
                val nextPath = nextTestRoute.drop(1)
                val nextPathString = nextPath.joinToString(separator = "->")
                Log.i("MyTag:PhysicalButtonTestT1", "nextPath: $nextPath")
                Log.i("MyTag:PhysicalButtonTestT1", "nextPathString: $nextPathString")

                var nextRouteWithArguments = "aaaa"
                if (nextPathString.isNotEmpty()) {
                    nextRouteWithArguments = "${nextTestRoute[0]}/$nextPathString"
                    Log.i("Check", "nextRouteWithArguments: $nextRouteWithArguments")
                } else {
                    nextRouteWithArguments = "${nextTestRoute[0]}"
                }

                navController.navigate(nextRouteWithArguments)
            } else if (runningTestMode)
                onTestComplete()
            else{
                Log.i("MyTag:PhysicalButtonTestT1", "delay(1000)")
                delay(1000)
                navController.popBackStack()}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Physical Button Test 1") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },

        ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Press Volume Up/Down button to test", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(16.dp))
                Log.i(volumeUpPressed.value.toString(), "Volume Up Pressed")
                Log.i(volumeDownPressed.value.toString(), "Volume Down Pressed")
                if (volumeUpPressed.value == true) {
                    Text(text = "Volume Up button pressed", style = MaterialTheme.typography.h5)
                }
                if (volumeDownPressed.value == true) {
                    Text(text = "Volume Down button pressed", style = MaterialTheme.typography.h5)
                }

            }
        }
    }


}
