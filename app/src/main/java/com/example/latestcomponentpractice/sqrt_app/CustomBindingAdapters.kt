package com.example.latestcomponentpractice.sqrt_app

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter


@BindingAdapter("setBackgroundColor")
fun ConstraintLayout.setBackgroundColor(color : Int) {
    this.setBackgroundColor(color)
}


