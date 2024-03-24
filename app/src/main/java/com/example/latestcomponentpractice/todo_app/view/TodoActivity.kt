package com.example.latestcomponentpractice.todo_app.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityTodoBinding
import com.example.latestcomponentpractice.todo_app.adapters.ToDoAdapter
import com.example.latestcomponentpractice.todo_app.model.Person
import com.example.latestcomponentpractice.todo_app.view_model.TodoActivityViewModel

class TodoActivity : AppCompatActivity() {

    private val viewBinding : ActivityTodoBinding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }

    private val viewModel: TodoActivityViewModel by lazy {
        ViewModelProvider(this)[TodoActivityViewModel::class.java]
    }

    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(viewBinding.root)


        // LiveData + ViewModel + ViewBinding
        // LiveData + ViewModel + DataBinding

        viewBinding.apply {
            toDoViewModel = viewModel
            personRecyclerViewId.layoutManager = LinearLayoutManager(this@TodoActivity)
            toDoAdapter = ToDoAdapter(mutableListOf<Person>())
            personRecyclerViewId.adapter =  toDoAdapter
        }

        viewModel.personLiveData.observe(this) {
            toDoAdapter.notifyDataChange(it)
        }

    }
}