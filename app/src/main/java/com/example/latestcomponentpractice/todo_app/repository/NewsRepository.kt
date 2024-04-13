package com.example.latestcomponentpractice.todo_app.repository

class NewsRepository(private val newsAPI: NewsAPI) {
    suspend fun getMPNews() : String? {
        val result = newsAPI.getMPNews()
        return result.body()
    }
}