package com.example.latestcomponentpractice.di

import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun saveUser(email : String, password : String) {
        println("Saving the User....")
    }
}