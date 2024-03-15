package com.teclast_korea.teclast_qc_application.ui.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun PulsatingButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    minScale: Float = 0.95f,
    maxScale: Float = 1.05f,
    durationMillis: Int = 250
) {
    val infiniteTransition = rememberInfiniteTransition()
    val pulseAnimation by infiniteTransition.animateFloat(
        initialValue = minScale,
        targetValue = maxScale,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .padding(16.dp)
            .scale(pulseAnimation),

    ) {
        Text(text = text)
    }
}


