package com.example.teclast_qc_application.device_tester.main_screen_sub

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teclast_qc_application.device_tester.main_screen_sub.test_filter.filterTestMode
import com.example.teclast_qc_application.ui.theme.ButtonWithJYEffect

@Composable
fun StandardTest(context: Context, navController: NavHostController) {
    val buttons = filterTestMode()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(buttons.size) { index ->
            val button = buttons[index]
            ButtonWithJYEffect(
                onClick = {
                    try {
                        navController.navigate("${button.first.lowercase().replace(" ", "_")}_screen")
                    }
                    catch (e: Exception){

                        Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show()
                    }


                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(140.dp),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = button.second,
                        contentDescription = button.first,
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = button.first,
                        style = TextStyle(fontSize = 30.sp, color = MaterialTheme.colors.onPrimary)
                    )
                }
            }
        }
    }
}
