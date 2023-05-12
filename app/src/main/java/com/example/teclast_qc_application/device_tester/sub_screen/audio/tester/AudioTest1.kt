package com.example.teclast_qc_application.device_tester.sub_screen.auido.tester

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teclast_qc_application.R
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AudioTestT1(context: Context, navController: NavController) {
    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
    leftMediaPlayer.setVolume(1f, 0f)
    val isLeftPlaying = remember { mutableStateOf(false) }

    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
    rightMediaPlayer.setVolume(0f, 1f)
    val isRightPlaying = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Audio Test") },
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
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FloatingActionButton(
                    modifier = Modifier.padding(start = 16.dp),
                    onClick = {
                        isLeftPlaying.value = !leftMediaPlayer.isPlaying
                        if(leftMediaPlayer.isPlaying){
                            leftMediaPlayer.pause()
                        }else{
                            leftMediaPlayer.start()
                        }
                    },
                    backgroundColor = if (isLeftPlaying.value) Color.Green else Color.Gray
                ) {
                    Text("Left")
                }
                Spacer(modifier = Modifier.width(40.dp))

                FloatingActionButton(
                    onClick = {
                        isRightPlaying.value = !rightMediaPlayer.isPlaying
                        if(rightMediaPlayer.isPlaying){
                            rightMediaPlayer.pause()
                        }else{
                            rightMediaPlayer.start()
                        }
                    },
                    backgroundColor = if (isRightPlaying.value) Color.Green else Color.Gray
                ) {
                    Text("Right")
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Press the buttons to play sounds on the left or right speaker.")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            leftMediaPlayer.release()
            rightMediaPlayer.release()
        }
    }
}


//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun AudioTestT1(context: Context, navController: NavController) {
//    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
//    leftMediaPlayer.setVolume(1f, 0f)
//
//    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
//    rightMediaPlayer.setVolume(0f, 1f)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Audio Test") },
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
//            Row(
//                modifier = Modifier.padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                FloatingActionButton(onClick = {
//                    if(leftMediaPlayer.isPlaying){
//                        leftMediaPlayer.pause()
//                    }else{
//                        leftMediaPlayer.start()
//                    }
//                }) {
//                    Text("Left")
//                }
//
//                FloatingActionButton(onClick = {
//                    if(rightMediaPlayer.isPlaying){
//                        rightMediaPlayer.pause()
//                    }else{
//                        rightMediaPlayer.start()
//                    }
//                }) {
//                    Text("Right")
//                }
//            }
//        }
//    ) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = "Press the buttons to play sounds on the left or right speaker.")
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            leftMediaPlayer.release()
//            rightMediaPlayer.release()
//        }
//    }
//}




//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun AudioTestT1(context: Context, navController: NavController) {
//    val leftMediaPlayer = MediaPlayer.create(context, R.raw.left_sound)
//    leftMediaPlayer.setVolume(1f, 0f)
//
//    val rightMediaPlayer = MediaPlayer.create(context, R.raw.right_sound)
//    rightMediaPlayer.setVolume(0f, 1f)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Audio Test") },
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
//            Row(
//                modifier = Modifier.padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                FloatingActionButton(onClick = {
//                    leftMediaPlayer.start()
//                }) {
//                    Text("Left")
//                }
//
//                FloatingActionButton(onClick = {
//                    rightMediaPlayer.start()
//                }) {
//                    Text("Right")
//                }
//            }
//        }
//    ) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = "Press the buttons to play sounds on the left or right speaker.")
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            leftMediaPlayer.release()
//            rightMediaPlayer.release()
//        }
//    }
//}
