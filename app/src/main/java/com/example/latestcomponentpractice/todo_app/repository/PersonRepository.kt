package com.example.latestcomponentpractice.todo_app.repository

import com.example.latestcomponentpractice.todo_app.model.Person
import com.example.latestcomponentpractice.todo_app.room_db.PersonDAO

class PersonRepository(private val dao : PersonDAO) {
    val persons = dao.getAllPerson()

    suspend fun insert(person : Person) : Long {
        return dao.insertPerson(person)
    }

    suspend fun update(person: Person) {
        dao.updatePerson(person)
    }

    suspend fun delete(person: Person) {
        return dao.deletePerson(person)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}