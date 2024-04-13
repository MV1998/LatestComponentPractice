package com.example.latestcomponentpractice.todo_app.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object NewsRetrofitHelper {

    fun getNewsRetrofitHelper() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.bhaskar.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}