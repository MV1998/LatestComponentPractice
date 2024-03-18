package com.example.latestcomponentpractice.Models

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AcerMonitorBox(
    override val height: Double,
    override val width: Double,
    override val id: Int) : Box() {

    override suspend fun startPackaging() {
        withContext(Dispatchers.IO) {
            if (!isPackaged) {
                println("$name is being packaged $id")
                Thread.sleep(2000)
                println("$name has been packaged $id")
            }
        }
    }
}