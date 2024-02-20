package com.example.teclast_qc_application.device_tester.specific_test.touch_panel

//make a screen for cpu test
//import com.example.teclast_qc_application.device_tester.testFunction.cpu.tester.getCurrentCpuUsage
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TouchPanelTestScreen(context: Context, navController: NavController) {
    // make a mutable state for touch panel test result
    // make this variable to get the result from the test function
    val touchPanelTest1Result = remember { mutableStateOf<String>("") }
    val touchPanelTest2Result = remember { mutableStateOf<String>("") }
    val touchPanelTest3Result = remember { mutableStateOf<String>("") }
    val touchPanelTest4Result = remember { mutableStateOf<String>("") }
    val touchPanelTest5Result = remember { mutableStateOf<String>("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Touch Panel Test") },
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

                //make a button for Touch test T1
                Button(onClick = {
                    navController.navigate("touch_panel_test_t1_screen") {

                    }
                }) {
                    Text(text = "Touch Test T1(Just Touch)")

                }

                Text(
                    text = touchPanelTest1Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                //make a button for Touch test T2
                Button(onClick = {
                    navController.navigate("touch_panel_test_t2_screen/notNextTest") {


                    }
                }) {
                    Text(text = "Touch Test T2(Edge and Diagonal)")
                }

                Text(
                    text = touchPanelTest2Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )


                //make a button for Touch test T3
                Button(onClick = {
                    navController.navigate("touch_panel_test_t3_screen/notNextTest") {


                    }
                }) {
                    Text(text = "Touch Test T3(Pinch and Zoom)")
                }

                Text(
                    text = touchPanelTest3Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // make a button for Touch test T4
                Button(onClick = {
                    navController.navigate("touch_panel_test_t4_screen/notNextTest") {


                    }
                }) {
                    Text(text = "Touch Test T4(Multi Touch Capability)")
                }
                Text(
                    text = touchPanelTest4Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Button(onClick = {
                    navController.navigate("touch_panel_test_t5_screen/notNextTest")
                }) {
                    Text(text = "Touch Test T5(Copy and Paste)")
                }
                Text(
                    text = touchPanelTest5Result.value,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}