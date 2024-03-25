package com.example.latestcomponentpractice.todo_app.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.latestcomponentpractice.todo_app.model.Person

@Dao
interface PersonDAO {

    @Insert
    suspend fun insertPerson(person : Person) : Long

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("DELETE FROM person")
    suspend fun deleteAll()

    @Query("SELECT * FROM person")
    fun getAllPerson() : LiveData<MutableList<Person>>

}