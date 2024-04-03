package com.example.latestcomponentpractice.sqrt_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.latestcomponentpractice.databinding.ActivitySqrtBinding
import com.example.latestcomponentpractice.sqrt_app.view_model.SqrtViewModel

class SqrtActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySqrtBinding
    private lateinit var sqrtViewModel: SqrtViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        binding = ActivitySqrtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sqrtViewModel = ViewModelProvider(this)[SqrtViewModel::class.java]

        binding.sqrtViewModel = sqrtViewModel
        binding.lifecycleOwner = this
    }
}