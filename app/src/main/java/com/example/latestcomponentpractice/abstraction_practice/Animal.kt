package com.example.latestcomponentpractice.abstraction_practice


//primary constructor
abstract class Animal {
     abstract var type : String
     var size : Int? = null
     var weight : Double? = null

    //secondary constructor
    constructor(size : Int, weight : Double) {
        this.size = size
        this.weight = weight
    }

    abstract fun move(speed : String)

    abstract fun makeNoise()

    fun getExplicitType() : String {
        return javaClass.simpleName + " " + type;
    }
}