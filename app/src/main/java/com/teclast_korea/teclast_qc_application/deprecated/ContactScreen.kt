package com.teclast_korea.teclast_qc_application.deprecated

//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment.Companion.CenterVertically
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.teclast_qc_application.test_result.test_results_db.AddTestResultDialog
//import com.example.teclast_qc_application.test_result.test_results_db.SortType
//import com.example.teclast_qc_application.test_result.test_results_db.TestResultEvent
//import com.example.teclast_qc_application.test_result.test_results_db.TestResultState
//
//
//@Composable
//fun ContactScreen(
//    state: TestResultState,
//    onEvent: (TestResultEvent) -> Unit,
//    navController: NavController
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "DataBase Screen") },
//                backgroundColor = MaterialTheme.colors.primaryVariant,
//                contentColor = MaterialTheme.colors.onPrimary,
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                backgroundColor = Color(0xFF6200EE), // Explicitly set the color here
//                contentColor = Color(0xFF6200EE),
//                onClick = {
//                    onEvent(TestResultEvent.ShowDialog)
//                }) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add contact",
//                )
//            }
//        },
//    ) { _ ->
//        //AddTestResultV2(state = state, onEvent = onEvent)
//
//        if (state.isAddingContact) {
//            AddTestResultDialog(state = state, onEvent = onEvent)
//        }
//
//        LazyColumn(
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier.fillMaxSize(),
//
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//
//        ) {
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .horizontalScroll(rememberScrollState()),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    SortType.values().forEach { sortType ->
//                        Row(
//                            modifier = Modifier
//                                .clickable {
//                                    onEvent(TestResultEvent.SortContacts(sortType))
//                                },
//                            verticalAlignment = CenterVertically
//                        ) {
//                            RadioButton(
//                                selected = state.sortType == sortType,
//                                onClick = {
//                                    onEvent(TestResultEvent.SortContacts(sortType))
//                                }, colors = RadioButtonDefaults.colors(
//                                    selectedColor = Color.Green,
//                                    unselectedColor = MaterialTheme.colors.onPrimary
//                                )
//                            )
//                            Text(text = sortType.name)
//                        }
//                    }
//                }
//            }
//            items(state.testResults) { contact ->
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Column(
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Text(
//                                text = contact.itemName,
//                                fontSize = 20.sp
//                            )
//                            Text(
//                                text = contact.testResult,
//                                fontSize = 20.sp,
//                                color = if (contact.testResult == "Pass") Color.Green else Color.Red,
//                            )
//                        }
//                        Text(text = contact.testDate, fontSize = 12.sp)
//                    }
//                    IconButton(onClick = {
//                        onEvent(TestResultEvent.DeleteTestResult(contact))
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = "Delete contact"
//                        )
//                    }
//                }
//            }
//        }
//    }
//}