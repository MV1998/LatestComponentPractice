package com.example.latestcomponentpractice.ViewModel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.latestcomponentpractice.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UserViewModel(private val initialState : ViewModelState) : ViewModel() {

    private val _userLiveData : MutableLiveData<ViewModelState> = MutableLiveData()
    val userLiveData : LiveData<ViewModelState>
        get() = _userLiveData

    fun receiveAllDataFromServer()  {
        _userLiveData.postValue(initialState)
//        Handler(Looper.myLooper()!!).apply {
//            postDelayed({
//                userLiveData.postValue(ViewModelState.Loaded(UserRepository.receiveAllUsers()))
//            }, 5000)
//        }
        viewModelScope.apply {
            launch(Dispatchers.IO) {
                delay(5000)
                Log.d(javaClass.simpleName, "receiveAllDataFromServer: ")
                _userLiveData.postValue(ViewModelState.Loaded(UserRepository.receiveAllUsers()))
            }
        }
    }
}
