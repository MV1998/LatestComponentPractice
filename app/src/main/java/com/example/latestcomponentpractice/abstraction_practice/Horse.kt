package com.example.latestcomponentpractice.abstraction_practice

import android.util.Log

class Horse(override var type: String, size : Int, weight : Double) : Mammal(type, size, weight) {

    override fun shedHair() {
        Log.d(javaClass.simpleName, "shedHair: golding powerful hair.")
    }

    override fun makeNoise() {
        Log.d(javaClass.simpleName, "makeNoise: " + getExplicitType() + " is making noise.")
    }

    override fun move(speed: String) {
        if (speed == "slow") {
            Log.d(javaClass.simpleName, "move: walking")
        }else {
            Log.d(javaClass.simpleName, "move: running")
        }
    }

}