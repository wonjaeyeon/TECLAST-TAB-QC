package com.example.teclast_qc_application.home.pdf_export.view_pdf

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ZoomableImage(bitmap: Bitmap) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(0f) }
    val angle = remember { mutableStateOf(0f) }
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .clip(RectangleShape) // Clip the box content
            .fillMaxSize() // Give the size you want...
            .background(Color.Red)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                    val x = pan.x * zoom
                    val y = pan.y * zoom
                    val angleRad = angle.value * PI / 180.0

                    offsetX.value += (x * cos(angleRad) - y * sin(angleRad)).toFloat()
                    offsetY.value += (x * sin(angleRad) + y * cos(angleRad)).toFloat()
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center) // keep the image centralized into the Box
                .graphicsLayer(
                    scaleX = scale.value.coerceIn(.5f, 3f),
                    scaleY = scale.value.coerceIn(.5f, 3f),
                    translationX = offsetX.value,
                    translationY = offsetY.value
                    // rotationZ = rotationState.value // Not needed for now
                )
                .background(Color.Yellow)
                .fillMaxSize(),
            contentDescription = null,
            bitmap = bitmap.asImageBitmap()
        )
    }
}