package com.illiouchine.timercompose.ui.composable.chono

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.timercompose.ui.theme.Purple700
import com.illiouchine.timercompose.ui.theme.TimerComposeTheme


/**
 * All credit to GerardPaligot
 * Github : https://github.com/GerardPaligot/android-countdown
 * Article : https://medium.com/@GerardPaligot/jetpack-compose-how-to-play-with-canvas-36d3638996b6
 */

@ExperimentalMaterialApi
@Composable
fun Chrono(
    seconds: Int,
    totalSeconds: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.h6,
    textColor: Color = Color.Gray,
    onTap: () -> Unit = {},
    onDoubleTap: () -> Unit = {},
    onLongPress: () -> Unit = {},
) {
    val progressAngle by animateFloatAsState(
        targetValue = 360f / totalSeconds.toFloat() * seconds,
        animationSpec = tween(500)
    )

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { /* Called when the gesture starts */ },
                    onDoubleTap = { onDoubleTap() },
                    onLongPress = { onLongPress() },
                    onTap = { onTap() }
                )
            }
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Brush.radialGradient(listOf(Color.Gray, Color.White))),
        contentAlignment = Alignment.Center
    ) {
        CircleMarkers(
            totalSeconds = totalSeconds,
            seconds = seconds
        )
        CircleProgress(
            angle = progressAngle,
            progressColor = listOf( Color.DarkGray,Color.Gray,Color.LightGray),
            backgroundProgressColor = listOf(Color.Transparent,Color.Transparent),
            centerColor = Color.White,
        )
        Text(
            text = "${seconds}s",
            style = textStyle,
            color = textColor,
            modifier = Modifier
                .align(alignment = Alignment.Center)
        )
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun ChronoPreviewFull() {
    TimerComposeTheme {
        Chrono(seconds = 10, totalSeconds = 500)
    }
}