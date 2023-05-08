package com.example.teclast_qc_application.ui.theme

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.70f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimatedButton() {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 1f else 0.9f)

        Button(
            onClick = {  },
            modifier = Modifier
                .scale(scale.value)
                .height(40.dp)
                .width(200.dp)
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selected.value = true }

                        MotionEvent.ACTION_UP  -> {
                            selected.value = false }
                    }
                    true
                }
        ) {
            Text(text = "Explore Airbnb", fontSize = 15.sp, color = Color.White)
        }
    }


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ButtonWithJYEffect(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.95f else 1f)
    val contentColor by colors.contentColor(enabled)
    Surface(
        onClick = onClick,
        modifier = modifier.scale(scale.value).pointerInteropFilter {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    selected.value = true }

                MotionEvent.ACTION_UP  -> {
                    onClick()
                    selected.value = false }
            }
            true
        },
        enabled = enabled,
        shape = shape,
        color = colors.backgroundColor(enabled).value,
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
        interactionSource = interactionSource,
    ) {
        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(
                value = MaterialTheme.typography.button
            ) {
                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        )
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}


//@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
//@Composable
//fun ButtonWithJYEffect2(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    elevation: ButtonElevation? = ButtonDefaults.elevation(),
//    shape: Shape = MaterialTheme.shapes.small,
//    border: BorderStroke? = null,
//    colors: ButtonColors = ButtonDefaults.buttonColors(),
//    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//    content: @Composable RowScope.() -> Unit
//) {
//    val selected = remember { mutableStateOf(false) }
//    val scale = animateFloatAsState(if (selected.value) 0.95f else 1f)
//    val contentColor by colors.contentColor(enabled)
//    Surface(
//        onClick = onClick,
//        modifier = modifier.scale(scale.value).pointerInteropFilter {
//            when (it.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    selected.value = true
//                    true
//                }
//                MotionEvent.ACTION_UP -> {
//                    onClick()
//                    selected.value = false
//                    false // Let the click event propagate to the Surface
//                }
//                else -> false
//            }
//        },
//        enabled = enabled,
//        shape = shape,
//        color = colors.backgroundColor(enabled).value,
//        contentColor = contentColor.copy(alpha = 1f),
//        border = border,
//        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
//        interactionSource = interactionSource,
//    ) {
//        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
//            ProvideTextStyle(
//                value = MaterialTheme.typography.button
//            ) {
//                Row(
//                    Modifier
//                        .defaultMinSize(
//                            minWidth = ButtonDefaults.MinWidth,
//                            minHeight = ButtonDefaults.MinHeight
//                        )
//                        .padding(contentPadding),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically,
//                    content = content
//                )
//            }
//        }
//    }
//}
