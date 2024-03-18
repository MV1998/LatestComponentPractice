package com.example.latestcomponentpractice.ViewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latestcomponentpractice.Repository.UserRepository



class UserViewModel : ViewModel() {

    var userLiveData : MutableLiveData<ViewModelState> = MutableLiveData()

    fun receiveAllDataFromServer() {
        userLiveData.postValue(ViewModelState.Loading)
        Handler(Looper.myLooper()!!).apply {
            postDelayed({
                userLiveData.postValue(ViewModelState.Loaded(UserRepository.receiveAllUsers()))
            }, 5000)
        }
    }
}