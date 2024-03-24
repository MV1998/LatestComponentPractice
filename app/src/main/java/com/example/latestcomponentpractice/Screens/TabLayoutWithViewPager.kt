package com.example.latestcomponentpractice.Screens

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.latestcomponentpractice.Adapters.ViewPagerFragmentAdapter
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityTabLayoutWithViewPagerBinding
import com.example.latestcomponentpractice.fragments.IntroductionFragment
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutWithViewPager : AppCompatActivity() {

    private val viewBinding : ActivityTabLayoutWithViewPagerBinding by lazy {
        ActivityTabLayoutWithViewPagerBinding.inflate(layoutInflater)
    }

    private val tabs = arrayOf("Books", "Games", "Movies")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(viewBinding.root)

        viewBinding.tabLayoutViewPagerId.adapter = ViewPagerFragmentAdapter(this)

        TabLayoutMediator(viewBinding.tabLayoutID, viewBinding.tabLayoutViewPagerId) {
            tab, position -> tab.text = tabs[position]
        }.attach()


        val sharedPreferences = getSharedPreferences("Nmae", MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putBoolean("name", true)
        editor.apply()
    }

    // categoris ed
}