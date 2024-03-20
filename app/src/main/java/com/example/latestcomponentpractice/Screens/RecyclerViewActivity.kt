package com.example.latestcomponentpractice.Screens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latestcomponentpractice.Adapters.RecyclerViewAdapter
import com.example.latestcomponentpractice.Models.AcerMonitorBox
import com.example.latestcomponentpractice.Models.AmazonDeliveryBox
import com.example.latestcomponentpractice.Models.Box
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val boxList : ArrayList<Box> = ArrayList()
        repeat(100) {
            if (it % 2 == 0) {
                boxList.add(AcerMonitorBox(id = it, height = 100.0, width = 100.0).apply {
                    name = "AcerMonitor $it"
                    isPackaged = it % 3 == 0
                })
            }else{
                boxList.add(AmazonDeliveryBox(id = it, height = 100.0, width = 100.0).apply {
                    name = "Amazon Delivery Box $it"
                    isPackaged = it % 5 == 0
                })
            }
        }

        binding.apply {
            val adapter = RecyclerViewAdapter(this@RecyclerViewActivity, boxList)
            recyclerView.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }
}