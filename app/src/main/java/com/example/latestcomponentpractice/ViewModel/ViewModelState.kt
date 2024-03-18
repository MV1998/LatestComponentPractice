package com.example.latestcomponentpractice.ViewModel

sealed class ViewModelState {
    data object Loading : ViewModelState()
    data class Loaded(val data : List<String>) : ViewModelState()

}