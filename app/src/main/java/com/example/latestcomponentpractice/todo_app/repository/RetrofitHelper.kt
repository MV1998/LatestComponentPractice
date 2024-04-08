package com.example.latestcomponentpractice.todo_app.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://quotable.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}