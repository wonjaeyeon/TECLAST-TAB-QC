package com.example.teclast_qc_application.device_tester.specific_test.g_sensor.tester

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*


class GSensorView(context: Context) : View(context), SensorEventListener {
    public val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    public val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    var sensorValues: List<Float> = listOf(0f, 0f, 0f)

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        sensorValues = event.values.toList()
        postInvalidate()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // handle if needed
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        sensorManager.unregisterListener(this)
    }
}
//
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun GSensorTestT1(context: Context, navController: NavController) {
//    val sensorValues = remember { mutableStateOf(listOf(0f, 0f, 0f)) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "G-Sensor T1 Test") },
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
//        content = {
//            val gSensorView = remember {
//                GSensorView(context).apply {
//                    sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
//                }
//            }
//
//            AndroidView({ gSensorView }) { view ->
//                sensorValues.value = view.sensorValues
//            }
//
//            DisposableEffect(Unit) {
//                onDispose {
//                    gSensorView.sensorManager.unregisterListener(gSensorView)
//                }
//            }
//
//            Column {
//                Text(text = "G-sensor Test")
//                Text(text = "X: ${sensorValues.value[0]}")
//                Text(text = "Y: ${sensorValues.value[1]}")
//                Text(text = "Z: ${sensorValues.value[2]}")
//            }
//        })
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GSensorTestT1(context: Context, navController: NavController) {
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val sensorValues = remember { mutableStateOf(listOf(0f, 0f, 0f)) }

    val sensorListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                sensorValues.value = event.values.toList()
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // handle if needed
            }
        }
    }


    DisposableEffect(sensorListener) {
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Accelerometer Test") },
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
        content = {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Accelerometer Test")
                Text(text = "X: ${sensorValues.value[0]}")
                Text(text = "Y: ${sensorValues.value[1]}")
                Text(text = "Z: ${sensorValues.value[2]}")
            }
        }
    )
}



//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun GSensorTestT1(context: Context, navController: NavController) {
//    val gSensorView = remember { GSensorView(context) }
//    val sensorValues = remember { mutableStateOf(listOf(0f, 0f, 0f)) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "G-Sensor T1 Test") },
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
//        content = {
//            AndroidView({ gSensorView }) { view ->
//                sensorValues.value = view.sensorValues
//            }
//
//            Column {
//                Text(text = "G-sensor Test")
//                Text(text = "X: ${sensorValues.value[0]}")
//                Text(text = "Y: ${sensorValues.value[1]}")
//                Text(text = "Z: ${sensorValues.value[2]}")
//            }
//        })
//}


