package com.example.latestcomponentpractice.sqrt_app.view_model

import android.graphics.Color
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.math.sqrt
import kotlin.random.Random

class SqrtViewModel : ViewModel(), Observable {

    @Bindable
    val number = MutableLiveData<String>()

    @Bindable
    val buttonStartStop = MutableLiveData<String>()

    private val _squareRootOfNumber = MutableLiveData<String>()

    val squareRootOfNumber : LiveData<String>
        get() = _squareRootOfNumber

     private val _backgroundColor = MutableLiveData<Int>()

    val backgroundColor : LiveData<Int>
        get() = _backgroundColor



    private var generateNumberJob : Job? = null

    fun calculateSquareRoot() {
        number.value?.let {
            if (it.isDigitsOnly()) {
                _squareRootOfNumber.value = sqrt(it.toDouble()).toString()
                clearAllEditText()
            }
        }
    }

    private fun clearAllEditText() {
        number.value = ""
    }

    fun startGenerateRandomNumberInEveryTenSeconds() {
        if (buttonStartStop.value == "Start Generating") {
            buttonStartStop.value = "Stop Generating"
            generateNumberJob = viewModelScope.launch {
                produceNumber()
                    .flowOn(Dispatchers.IO)
                    .collect{
                        Log.d(javaClass.simpleName, "startGenerateRandomNumberInEveryTenSeconds: $it")
                        _backgroundColor.value = Color.parseColor("#$it")
                        _squareRootOfNumber.value = sqrt(it.toDouble()).toString()
                    }
            }
        }else {
            stopGenerateNumber()
            buttonStartStop.value = "Start Generating"
        }
    }

    private fun stopGenerateNumber() {
        generateNumberJob?.let {
            it.cancel()
        }
    }

    private fun produceNumber() : Flow<Int> {
        return flow {
            while (true) {
                emit(Random.nextInt(111111, 999999))
                delay(50)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopGenerateNumber()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    init {
        buttonStartStop.value = "Start Generating"
        _backgroundColor.value = Color.parseColor("#000000")
    }
}