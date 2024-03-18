package com.example.latestcomponentpractice.abstraction_practice

import android.util.Log

class ElectronicProduct(override val type: String,
                        override val price: Double,
                        override val description: String) : ProductForSale() {
    override fun details() {
        Log.d(javaClass.simpleName,"product type ${type}, description - $description, price - $price")
    }
}
