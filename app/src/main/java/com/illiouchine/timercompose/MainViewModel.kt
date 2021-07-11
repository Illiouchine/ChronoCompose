package com.illiouchine.timercompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


const val TOTAL_INITIAL_TIMES = 120

class MainViewModel : ViewModel() {

    private var job: Job? = null
    private val _times: MutableStateFlow<Int> = MutableStateFlow(0)
    val times = _times.asStateFlow()

    fun start(times: Int = TOTAL_INITIAL_TIMES) {
        if (_times.value == 0) {
            _times.value = times
        }
        job?.cancel()
        job = launchTimer()
    }

    fun stop() {
        job?.cancel()
        _times.value = 0
    }

    fun pause() {
        job?.cancel()
    }

    private fun launchTimer(): Job = viewModelScope.launch(Dispatchers.IO) {
        while (isActive) {
            if (_times.value <= 0){
                job?.cancel()
                return@launch
            }
            delay(timeMillis = 1000)
            _times.value -= 1
        }
    }
}