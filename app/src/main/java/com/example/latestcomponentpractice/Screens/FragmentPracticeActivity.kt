package com.example.latestcomponentpractice.Screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityFragmentPracticeBinding
import com.example.latestcomponentpractice.fragments.Fragment1
import com.example.latestcomponentpractice.fragments.Fragment2
import kotlin.math.log

class FragmentPracticeActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : ActivityFragmentPracticeBinding
    private lateinit var transaction : FragmentTransaction
    private val fragment1 : Fragment1 by lazy {
        Fragment1()
    }

    private val fragment2 : Fragment2 by lazy {
        Fragment2()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        binding = ActivityFragmentPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fragBtn1.setOnClickListener(this@FragmentPracticeActivity)
            fragBtn2.setOnClickListener(this@FragmentPracticeActivity)
        }
    }

    override fun onClick(view: View?) {

        transaction = supportFragmentManager.beginTransaction()
        when (view?.id) {
            R.id.fragBtn1 ->
                transaction.replace(
                 binding.frameLayout.id,
                    fragment1
                )
            R.id.fragBtn2 ->
                transaction.replace(
                    binding.frameLayout.id,
                    fragment2
                )
            else -> {}
        }
        //transaction.addToBackStack(R.id.fragBtn1.toString())
        transaction.commit()
    }
}