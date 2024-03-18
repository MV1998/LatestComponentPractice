package com.example.latestcomponentpractice.abstraction_practice

import android.util.Log

abstract class ProductForSale {
    abstract val type : String
    abstract val price : Double
    abstract val description : String

    fun getSalesPrice(quantity : Int) : Double {
        return  quantity * price
    }

    fun printPriceItem(quantity: Int) {
        Log.d(javaClass.simpleName, "quantity: $quantity - price $price")
    }


    abstract fun details()
}