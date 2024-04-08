package com.example.latestcomponentpractice.todo_app.repository

import com.example.latestcomponentpractice.todo_app.model.QuoteList
import kotlin.random.Random

class QuotesRepository(private val quotesAPI: QuotesAPI) {

    suspend fun getQuotes() : QuoteList? {
       val result = quotesAPI.getQuotes(Random.nextInt(1, 106))
        return result.body()
    }
}