package com.example.latestcomponentpractice.todo_app.interfaces

import com.example.latestcomponentpractice.todo_app.model.Person

interface ToDoItemClickListener {
    fun onClick(person : Person)
}