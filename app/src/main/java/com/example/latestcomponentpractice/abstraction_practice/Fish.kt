package com.example.latestcomponentpractice.abstraction_practice

import android.util.Log

class Fish(override var type: String, size: Int, weight : Double) : Animal(size, weight) {

    override fun move(speed: String) {
        if (speed == "slow") {
            Log.d(javaClass.simpleName,"fish is slowly walking ${getExplicitType()}")
        }else {
            Log.d(javaClass.simpleName, "fish is running ${getExplicitType()}")
        }
    }

    override fun makeNoise() {
        Log.d(javaClass.simpleName, "fish is making noise")
    }
}