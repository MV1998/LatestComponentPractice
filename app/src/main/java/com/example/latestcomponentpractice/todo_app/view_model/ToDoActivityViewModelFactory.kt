package com.example.latestcomponentpractice.todo_app.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latestcomponentpractice.todo_app.repository.PersonRepository
import com.example.latestcomponentpractice.todo_app.room_db.PersonDAO

class ToDoActivityViewModelFactory(private val repository : PersonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoActivityViewModel(repository) as T
    }
}