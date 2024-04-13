package com.example.latestcomponentpractice.todo_app.repository

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    @GET("/local/mp")
    suspend fun getMPNews() : Response<String>

}