package com.teclast_korea.teclast_qc_application.ui.device_tester.sub_screen

//import android.content.Context
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Assignment
//import androidx.compose.material.icons.filled.ElectricBolt
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.teclast_korea.teclast_qc_application.ui.theme.ButtonWithJYEffect

//@Composable
//fun SenarioTest(context: Context, navController: NavHostController) {
//    val buttons = listOf(
//        Pair("Standard Test", Icons.Filled.Assignment),
//        Pair("Fast Test", Icons.Filled.ElectricBolt),
//    )
//
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(200.dp),
//        contentPadding = PaddingValues(8.dp)
//    ) {
//        items(buttons.size) { index ->
//            val button = buttons[index]
//            ButtonWithJYEffect(onClick = {
//                //navController.navigate("${button.first.lowercase().replace(" ", "_")}_screen")
//            },
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//                    .height(140.dp),
//
//                ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Icon(
//                        imageVector = button.second,
//                        contentDescription = button.first,
//                        tint = MaterialTheme.colors.onPrimary,
//                        modifier = Modifier.size(40.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = button.first,
//                        style = TextStyle(fontSize = 30.sp, color = MaterialTheme.colors.onPrimary)
//                    )
//                }
//            }
//        }
//    }
//}