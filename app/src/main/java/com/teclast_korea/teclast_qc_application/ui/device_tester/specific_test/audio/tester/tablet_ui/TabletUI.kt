package com.teclast_korea.teclast_qc_application.ui.device_tester.specific_test.audio.tester.tablet_ui

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teclast_korea.teclast_qc_application.R

@Composable
fun TabletUI(
    leftMediaPlayer: MediaPlayer,
    rightMediaPlayer: MediaPlayer,
    isLeftPlaying: MutableState<Boolean>,
    isRightPlaying: MutableState<Boolean>,
    sensorValues: MutableState<List<Float>>,
    size: Size,
    model: String
) {
    val gSensorXvalue = remember { mutableFloatStateOf(0f) }
    val gSensorYvalue = remember { mutableFloatStateOf(0f) }
    val speakerNumber = remember { mutableIntStateOf(2) }
    val messageToActivate = remember { mutableStateOf("") }
    val rotationAngle = remember { mutableStateOf(floatArrayOf(0f, 0f, 0f, 0f)) }
    val deviceRotationIsFixed = remember { mutableStateOf(false) }

    when (model.uppercase()) {
        //S2
        "U10PRO" -> {
            gSensorXvalue.floatValue = 0.1f
            gSensorYvalue.floatValue = -0.6f
            speakerNumber.intValue = 2
            messageToActivate.value = "Press buttons to play sounds on the left or right speaker."
            rotationAngle.value = floatArrayOf(270f, 90f, 180f, 0f)
        }

        //S2
        "T10PRO" -> {
            gSensorXvalue.floatValue = 0.1f
            gSensorYvalue.floatValue = -0.6f
            speakerNumber.intValue = 2
            messageToActivate.value = "Press buttons to play sounds on the left or right speaker."
            rotationAngle.value = floatArrayOf(270f, 90f, 180f, 0f)
        }

        //S2
        "ITAB_X40L_PLUS" -> {
            gSensorXvalue.floatValue = 0.1f
            gSensorYvalue.floatValue = 0.1f
            speakerNumber.intValue = 1
            messageToActivate.value = "Press buttons to play sounds on the speaker."
            rotationAngle.value = floatArrayOf(90f, 270f, 0f, 180f)
        }

        //S2
        "TORDER_PAD" -> {
            gSensorXvalue.floatValue = 0.1f
            gSensorYvalue.floatValue = 0.1f
            speakerNumber.intValue = 1
            messageToActivate.value = "Press buttons to play sounds on the speaker."
            rotationAngle.value = floatArrayOf(90f, 270f, 0f, 180f)
        }

        //C2
        "R10D" -> {
            gSensorXvalue.floatValue = -1.3f
            gSensorYvalue.floatValue = -1.6f
            speakerNumber.intValue = 2
            messageToActivate.value = "Press buttons to play sounds on the left or right speaker."
            rotationAngle.value = floatArrayOf(0f, 0f, 90f, 0f) //  //4: 90f
            deviceRotationIsFixed.value = true
        }

        else -> {
            gSensorXvalue.floatValue = 0.1f
            gSensorYvalue.floatValue = 0.1f
            speakerNumber.intValue = 2
            messageToActivate.value = "Press buttons to play sounds on the left or right speaker."
            rotationAngle.value = floatArrayOf(90f, 270f, 0f, 180f)
        }
    }

    fun rotateUI(): Modifier {
        if (model == "R10D") {
            return when {
                sensorValues.value[1] > gSensorYvalue.value && sensorValues.value[0] > gSensorXvalue.value -> Modifier.padding(0.dp)
                    .rotate(rotationAngle.value[0])

                sensorValues.value[1] > gSensorYvalue.value && sensorValues.value[0] < gSensorXvalue.value -> Modifier.padding(0.dp)
                    .rotate(rotationAngle.value[1])

                sensorValues.value[0] < gSensorXvalue.value && sensorValues.value[1] < gSensorYvalue.value -> Modifier.padding(0.dp)
                    .rotate(rotationAngle.value[2])

                sensorValues.value[0] < gSensorXvalue.value && sensorValues.value[1] > gSensorYvalue.value -> Modifier.padding(0.dp)
                    .rotate(rotationAngle.value[3])

                else -> Modifier.padding(0.dp).rotate(rotationAngle.value[3])
            }
        }

        return when {
            size.width > size.height && sensorValues.value[0] > gSensorXvalue.value -> Modifier.padding(0.dp)
                .rotate(rotationAngle.value[0])

            size.width > size.height && sensorValues.value[0] < gSensorXvalue.value -> Modifier.padding(0.dp)
                .rotate(rotationAngle.value[1])

            size.width < size.height && sensorValues.value[1] < gSensorYvalue.value -> Modifier.padding(0.dp)
                .rotate(rotationAngle.value[2])

            size.width < size.height && sensorValues.value[1] > gSensorYvalue.value -> Modifier.padding(0.dp)
                .rotate(rotationAngle.value[3])

            else -> Modifier.padding(0.dp).rotate(rotationAngle.value[3])
        }
    }

    @Composable
    fun imageByRightLeftSpeaker(){
        var tabletPaintResource: Int

        when{
            isRightPlaying.value && isLeftPlaying.value -> {
                tabletPaintResource = R.drawable.tablet_right_on_left_on
            }
            isRightPlaying.value && !isLeftPlaying.value -> {
                tabletPaintResource = R.drawable.tablet_right_off_left_on
            }
            !isRightPlaying.value && isLeftPlaying.value -> {
                tabletPaintResource = R.drawable.tablet_right_on_left_off
            }
            !isRightPlaying.value && !isLeftPlaying.value -> {
                tabletPaintResource = R.drawable.tablet_right_off_left_off
            }
            else -> {
                tabletPaintResource = R.drawable.tablet
            }
        }

        return Image(
            painter = painterResource(id = tabletPaintResource),
            contentDescription = "My Image",
            modifier = Modifier.padding(8.dp)
                //.size(context.resources.displayMetrics.run{widthPixels/4}.dp, context.resources.displayMetrics.run{heightPixels/4}.dp) // Resize the image to 300dp
                .size(300.dp) // Resize the image to 300dp

        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = rotateUI()
    ) {
//        Text(text = "x : ${sensorValues.value[0]}")
//        Text(text = "y : ${sensorValues.value[1]}")
//        Text(text = if(size.height > size.width) "size.height > size.width" else "size.height < size.width")
        Text(text = "Model: $model")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = messageToActivate.value)
        Spacer(modifier = Modifier.height(16.dp))
        if (deviceRotationIsFixed.value) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(if (isRightPlaying.value) Color.Green else Color.Gray),
                    onClick = {
                        isRightPlaying.value = !rightMediaPlayer.isPlaying
                        if (rightMediaPlayer.isPlaying) {
                            rightMediaPlayer.pause()
                        } else {
                            rightMediaPlayer.start()
                        }
                    }
                ) {
                    //volume up
                    Icon(
                        Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Volume Up",
                        modifier = Modifier.graphicsLayer(scaleX = -1f)
                    ) // This line adds the icon and flips it horizontally

                    Text("Left", color = MaterialTheme.colors.onPrimary)
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(if (isLeftPlaying.value) Color.Green else Color.Gray),
                    onClick = {
                        isLeftPlaying.value = !leftMediaPlayer.isPlaying
                        if (leftMediaPlayer.isPlaying) {
                            leftMediaPlayer.pause()
                        } else {
                            leftMediaPlayer.start()
                        }
                    }
                ) {

                    Text("Right", color = MaterialTheme.colors.onPrimary)
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Volume Up") // This line adds the icon
                }
            }
            imageByRightLeftSpeaker()
//            Image(
//                painter = painterResource(id = R.drawable.tablet),
//                contentDescription = "My Image",
//                modifier = Modifier.padding(8.dp)
//                    //.size(context.resources.displayMetrics.run{widthPixels/4}.dp, context.resources.displayMetrics.run{heightPixels/4}.dp) // Resize the image to 300dp
//                    .size(300.dp) // Resize the image to 300dp
//                    .drawWithCache {
//                        val cameraX = 210
//                        val cameraY = 70
//                        val cameraRadius = 5.dp.toPx()
//                        onDrawWithContent {
//                            drawContent()
//                            drawCircle(
//                                color = Color.LightGray,
//                                radius = cameraRadius,
//                                center = Offset(cameraX.toFloat(), cameraY.toFloat())
//                            )
//                            drawRoundRect(
//                                color = if (isLeftPlaying.value) Color.Green else Color.Gray,
//                                size = Size(40.dp.toPx(), 10.dp.toPx()),
//                                cornerRadius = CornerRadius(5.dp.toPx(), 5.dp.toPx()),
//                                topLeft = Offset(315f, 50f)
//                            )
//
//                            drawRoundRect(
//                                color = if (isRightPlaying.value) Color.Green else Color.Gray,
//                                size = Size(40.dp.toPx(), 10.dp.toPx()),
//                                cornerRadius = CornerRadius(5.dp.toPx(), 5.dp.toPx()),
//                                topLeft = Offset(35f, 50f)
//                            )
//                        }
//                    }
//            )
        } else {
            Row() {
                Spacer(modifier = Modifier.width(70.dp))
                if (speakerNumber.value == 2) {
                    Button(
                        colors = ButtonDefaults.buttonColors(if (isLeftPlaying.value) Color.Green else Color.Gray),
                        onClick = {
                            isLeftPlaying.value = !leftMediaPlayer.isPlaying
                            if (leftMediaPlayer.isPlaying) {
                                leftMediaPlayer.pause()
                            } else {
                                leftMediaPlayer.start()
                            }
                        }
                    ) {
                        Text("Left", color = MaterialTheme.colors.onPrimary)
                        //volume up
                        Icon(
                            Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Volume Up",
                            modifier = Modifier.rotate(270f)
                        ) // This line adds the icon and flips it horizontally
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.tablet),
                contentDescription = "My Image",
                modifier = Modifier.padding(8.dp).rotate(-90f)
                    .size(300.dp) // Resize the image to 300dp
                    .drawWithCache {
                        val cameraX = 270 // Adjust these values based on the camera position in your image
                        val cameraY = 105
                        val cameraRadius = 5.dp.toPx()
                        onDrawWithContent {
                            drawContent()
                            drawCircle(
                                color = Color.LightGray,
                                radius = cameraRadius,
                                center = Offset(cameraX.toFloat(), cameraY.toFloat())
                            )
                            if (speakerNumber.value == 2) {
                                drawRoundRect(
                                    color = if (isLeftPlaying.value) Color.Green else Color.Gray,
                                    size = Size(10.dp.toPx(), 40.dp.toPx()),
                                    cornerRadius = CornerRadius(5.dp.toPx(), 5.dp.toPx()),
                                    topLeft = Offset(515f, 350f)
                                )
                            }
                            drawRoundRect(
                                color = if (isRightPlaying.value) Color.Green else Color.Gray,
                                size = Size(10.dp.toPx(), 40.dp.toPx()),
                                cornerRadius = CornerRadius(5.dp.toPx(), 5.dp.toPx()),
                                topLeft = Offset(-5f, 350f)
                            )
                        }
                    }
            )
            Row() {

                Spacer(modifier = Modifier.width(70.dp))
                Button(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(if (isRightPlaying.value) Color.Green else Color.Gray),
                    onClick = {
                        isRightPlaying.value = !rightMediaPlayer.isPlaying
                        if (rightMediaPlayer.isPlaying) {
                            rightMediaPlayer.pause()
                        } else {
                            rightMediaPlayer.start()
                        }
                    }
                ) {

                    Text("Right", color = MaterialTheme.colors.onPrimary)
                    Icon(
                        Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Volume Up",
                        modifier = Modifier.rotate(90f)
                    ) // This line adds the icon
                }
            }
        }
    }
}