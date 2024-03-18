package com.teclast_korea.teclast_qc_application.device_tester

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teclast_korea.teclast_qc_application.device_tester.sub_screen.SenarioTest
import com.teclast_korea.teclast_qc_application.device_tester.sub_screen.SpecificTest
import com.teclast_korea.teclast_qc_application.device_tester.sub_screen.StandardTest

@Composable
fun TesterScreen2(context: Context, navController: NavHostController) {

    var selectedOption = "Standard Test"


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        selectedOption = TriStateToggle2()
        Spacer(modifier = Modifier.height(8.dp))
        if (selectedOption == "Standard Test") {
            StandardTest(context, navController)
        }
        else if (selectedOption == "Specific Test") {

            SpecificTest(context, navController)
        }
        else if (selectedOption == "Seo Test") {
            SenarioTest(context, navController)
        }
        else {
            Row(
                Modifier
                    .fillMaxWidth()


                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Not Found", modifier = Modifier.align(Alignment.CenterVertically), style = MaterialTheme.typography.h4)
            }
        }

    }
}

@Composable
fun TriStateToggle2() : String{
    val states = listOf(
        "Standard Test",
        "Specific Test",
        //"Scenario Test"
    )
    var selectedOption by remember {
        mutableStateOf(states[0])
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    val selectedColor = Color.Green
    val unselectedColor = Color.LightGray

    Surface(
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        modifier = Modifier
            .wrapContentSize(),
                color = MaterialTheme.colors.primary
    ) {
        Row {
            states.forEach { text->
                Text(
                    text = text,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .clickable {
                            onSelectionChange(text)
                        }
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                    color = if (text == selectedOption) {
                        selectedColor
                    } else {
                        unselectedColor
                    }
                )
            }
        }
    }
    return selectedOption
}