package com.example.latestcomponentpractice.scoped_storage.api_service

import com.example.latestcomponentpractice.scoped_storage.models.UnsplashDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageAPI {
    @GET("/photos/random/")
    suspend fun getImage(@Query("client_id") clientId : String) : Response<UnsplashDataModel>
}

//j6Myvn8@in_R$LE