package com.example.latestcomponentpractice.Models

abstract class Box {
    abstract val id : Int
    abstract val height : Double
    abstract val width : Double
    var name : String = "Default Box"
    var isPackaged : Boolean = false
    var isDamaged : Boolean = false

    abstract suspend fun startPackaging()
}