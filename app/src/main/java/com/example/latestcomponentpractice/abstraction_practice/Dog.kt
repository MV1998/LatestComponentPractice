package com.example.latestcomponentpractice.abstraction_practice

import android.util.Log


class Dog(override var type: String, size: Int, weight: Double) : Animal(size, weight) {

    override fun move(speed: String) {
        if (speed == "slow") {
            Log.d(javaClass.simpleName, "$type is walking ${getExplicitType()}")
        }else {
            Log.d(javaClass.simpleName,"$type is running ${getExplicitType()}")
        }
    }

    override fun makeNoise() {
        Log.d(javaClass.simpleName, "dog is making noise")
    }
}