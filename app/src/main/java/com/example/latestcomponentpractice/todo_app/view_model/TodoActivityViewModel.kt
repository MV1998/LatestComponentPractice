package com.example.latestcomponentpractice.todo_app.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latestcomponentpractice.todo_app.model.Person

class TodoActivityViewModel : ViewModel() {

    private val personMutableLiveData = MutableLiveData<MutableList<Person>>()

    val personLiveData : LiveData<MutableList<Person>>
        get() = personMutableLiveData

    private var personList : MutableList<Person> = mutableListOf()

    fun addPerson(name : String, age : String) {
        Log.d(javaClass.simpleName, "addPerson: ")
        personList.add(Person(name, age.toInt()))
        personMutableLiveData.postValue(personList)
    }

}