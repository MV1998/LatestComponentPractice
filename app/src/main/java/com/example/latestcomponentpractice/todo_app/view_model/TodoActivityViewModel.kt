package com.example.latestcomponentpractice.todo_app.view_model

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latestcomponentpractice.databinding.ListViewCustomItemBinding
import com.example.latestcomponentpractice.todo_app.model.Person
import com.example.latestcomponentpractice.todo_app.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoActivityViewModel(private val repository: PersonRepository) : ViewModel() {

    val personLiveData = repository.persons

    val userNameObservable : ObservableField<String> = ObservableField<String>()

    fun addPerson(name : String, age : String) {
        insert(Person(name, age.toInt()))
    }

    private fun insert(person : Person) = viewModelScope.launch() {
        repository.insert(person)
    }

}

