package com.example.teclast_qc_application.device_tester.specific_test.physical_button.tester

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PhysicalButtonTestT1(context: Context, navController: NavController, volumeUpPressed : MutableState<Boolean>, volumeDownPressed : MutableState<Boolean>) {


    val checkIfClicked = remember { mutableStateOf(false) }

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
                if(volumeUpPressed.value== true){
                    Text(text = "Volume Up button pressed", style = MaterialTheme.typography.h5)
                }
                if(volumeDownPressed.value == true){
                    Text(text = "Volume Down button pressed", style = MaterialTheme.typography.h5)
                }

            }
        }
    }


}
