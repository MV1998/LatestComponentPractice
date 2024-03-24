package com.example.latestcomponentpractice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class UserViewModelProviderFactory(private val state : ViewModelState) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(state) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }

}