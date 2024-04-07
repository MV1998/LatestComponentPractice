package com.example.latestcomponentpractice.sqrt_app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.latestcomponentpractice.databinding.ActivitySqrtBinding
import com.example.latestcomponentpractice.sqrt_app.view_model.SqrtViewModel

class SqrtActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySqrtBinding
    private val sqrtViewModel: SqrtViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        binding = ActivitySqrtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sqrtViewModel = sqrtViewModel
        binding.lifecycleOwner = this
    }
}