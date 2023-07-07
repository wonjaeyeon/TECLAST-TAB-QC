package com.example.teclast_qc_application.test_result.room_db_ver2//package com.example.teclast_qc_application.test_result.room_db
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.AlertDialog
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//
//// Screen that allows the user to add a new contact to the database.
//@Composable
//fun AddContactDialog2(
//    state: ContactState,
//    onEvent: (ContactEvent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    AlertDialog(
//        modifier = modifier,
//        onDismissRequest = {
//            onEvent(ContactEvent.HideDialog)
//        },
//        title = { Text(text = "Add contact") },
//        text = {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                TextField(
//                    value = state.firstName,
//                    onValueChange = {
//                        onEvent(ContactEvent.SetFirstName(it))
//                    },
//                    placeholder = {
//                        Text(text = "First name")
//                    }
//                )
//                TextField(
//                    value = state.lastName,
//                    onValueChange = {
//                        onEvent(ContactEvent.SetLastName(it))
//                    },
//                    placeholder = {
//                        Text(text = "Last name")
//                    }
//                )
//                TextField(
//                    value = state.phoneNumber,
//                    onValueChange = {
//                        onEvent(ContactEvent.SetPhoneNumber(it))
//                    },
//                    placeholder = {
//                        Text(text = "Phone number")
//                    }
//                )
//            }
//        },
//        buttons = {
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.CenterEnd
//            ) {
//                Button(onClick = {
//                    onEvent(ContactEvent.SaveContact)
//                }) {
//                    Text(text = "Save")
//                }
//            }
//        }
//    )
//}