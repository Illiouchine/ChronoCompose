package com.illiouchine.timercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.timercompose.ui.composable.chono.Chrono
import com.illiouchine.timercompose.ui.theme.TimerComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerComposeTheme {
                val times by viewModel.times.collectAsState()
                Box(modifier = Modifier
                    .size(140.dp)
                    .background(Color.White)) {
                    Chrono(
                        seconds = times,
                        totalSeconds = TOTAL_INITIAL_TIMES,
                        textStyle = MaterialTheme.typography.h6,
                        onTap = { viewModel.start() },
                        onDoubleTap = { viewModel.pause() },
                        onLongPress = { viewModel.stop() }
                    )
                }
            }
        }
    }

}