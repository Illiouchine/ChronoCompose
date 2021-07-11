package com.illiouchine.timercompose.ui.composable.chono

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.timercompose.ui.theme.Purple200
import com.illiouchine.timercompose.ui.theme.Purple500
import com.illiouchine.timercompose.ui.theme.TimerComposeTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val nbMarker = 120
@Composable
internal fun CircleMarkers(
    totalSeconds : Int,
    seconds: Int
){
    for (i in 0 until nbMarker) {
        Marker(
            angle = i * (360 / nbMarker),
            active = i < seconds * (nbMarker / totalSeconds)
        )
    }
}

@Composable
internal fun Marker(
    angle: Int,
    active: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .drawBehind {
                val theta = (angle - 90) * PI.toFloat() / 180f
                val startRadius = size.width / 2 * .72f
                val endRadius = size.width / 2 * .8f
                val startPos = Offset(cos(theta) * startRadius, sin(theta) * startRadius)
                val endPos = Offset(cos(theta) * endRadius, sin(theta) * endRadius)
                drawLine(
                    color = if (active) Purple200 else Purple500.copy(alpha = .1f),
                    start = center + startPos,
                    end = center + endPos,
                    strokeWidth = 8f,
                    cap = StrokeCap.Round
                )
            }
    )
}


@Composable
@Preview
fun CircleMarkerPreview(){
    TimerComposeTheme {
        CircleMarkers(
            totalSeconds = 360,
            seconds = 120
        )
    }
}