package com.example.latestcomponentpractice.Screens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.latestcomponentpractice.Adapters.ViewPagerFragmentAdapter
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityViewPagerExampleBinding

class ViewPagerExample : AppCompatActivity() {

    private val viewBinding : ActivityViewPagerExampleBinding by lazy {
        ActivityViewPagerExampleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(viewBinding.root)


        viewBinding.viewPager.adapter = ViewPagerFragmentAdapter(this)
    }
}