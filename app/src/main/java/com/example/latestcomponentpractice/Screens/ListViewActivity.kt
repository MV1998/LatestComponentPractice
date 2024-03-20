package com.example.latestcomponentpractice.Screens

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.latestcomponentpractice.Adapters.ListViewCustomAdapter
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityListViewBinding
import com.example.latestcomponentpractice.extention_functions.addExclamation

class ListViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            Log.d(javaClass.simpleName, "onCreate: ${listView.hashCode()}")
            var dataSource = arrayListOf("Mohit", "vaibhav","simple", "another".addExclamation(),
                "Java 1", "Kotlin1", "Mondo DB", "NodeJS","Python", "React", "Angular", "Flutter","Android","Dart", "NumPy",
                "Java 1", "Kotlin1", "Mondo DB", "NodeJS","Python", "React", "Angular", "Flutter","Android","Dart", "NumPy",
                "Java 1", "Kotlin1", "Mondo DB", "NodeJS","Python", "React", "Angular", "Flutter","Android","Dart", "NumPy",
                "Java 1", "Kotlin1", "Mondo DB", "NodeJS","Python", "React", "Angular", "Flutter","Android","Dart", "NumPy")

//            val adapter = ArrayAdapter<String>(this@ListViewActivity, android.R.layout.simple_list_item_1, dataSource)
            val customAdapter = ListViewCustomAdapter(this@ListViewActivity, dataSource)

            listView.adapter = customAdapter
        }
    }

}