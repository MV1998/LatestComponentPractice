package com.example.latestcomponentpractice.Models

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AmazonDeliveryBox(
    override val height: Double,
    override val width: Double,
                        override val id: Int
) : Box() {

    override suspend fun startPackaging() : String{
        if (!isPackaged) {
            println("$name is being packaged $id")
            Thread.sleep(2000)
        }
        return "$name has been packaged $id"
    }
}