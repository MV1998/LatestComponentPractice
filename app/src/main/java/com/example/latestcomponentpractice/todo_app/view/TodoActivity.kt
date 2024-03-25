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
import com.example.latestcomponentpractice.todo_app.repository.PersonRepository
import com.example.latestcomponentpractice.todo_app.room_db.PersonDatabase
import com.example.latestcomponentpractice.todo_app.view_model.ToDoActivityViewModelFactory
import com.example.latestcomponentpractice.todo_app.view_model.TodoActivityViewModel

class TodoActivity : AppCompatActivity() {

    private val viewBinding : ActivityTodoBinding by lazy {
        ActivityTodoBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: TodoActivityViewModel

    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var database: PersonDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(viewBinding.root)

        database = PersonDatabase.getInstance(applicationContext)
        val repo = PersonRepository(database.personDAO)
        viewModel = ViewModelProvider(this, ToDoActivityViewModelFactory(repo))[TodoActivityViewModel::class.java]

        viewBinding.apply {
            toDoViewModel = viewModel
            personRecyclerViewId.layoutManager = LinearLayoutManager(this@TodoActivity)
            toDoAdapter = ToDoAdapter(viewModel.personLiveData.value ?: mutableListOf())
            personRecyclerViewId.adapter =  toDoAdapter
        }

        viewBinding.addBtn.setOnClickListener {
            viewModel.addPerson(viewBinding.editTextNameId.text.toString(), viewBinding.editTextAgeId.text.toString())
            clearTextField()
        }

        viewModel.personLiveData.observe(this) {
            toDoAdapter.notifyDataChange(it)
        }
    }

    private fun clearTextField() {
        viewBinding.apply {
            editTextAgeId.text.clear()
            editTextNameId.text.clear()
        }
    }
}