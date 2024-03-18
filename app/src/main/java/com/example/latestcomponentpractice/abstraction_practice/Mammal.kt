package com.example.latestcomponentpractice.abstraction_practice

abstract class Mammal(override var type: String, size : Int, weight : Double) : Animal(size, weight) {
    abstract fun shedHair()
}