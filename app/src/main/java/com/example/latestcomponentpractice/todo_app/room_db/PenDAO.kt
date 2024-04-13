package com.example.latestcomponentpractice.todo_app.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.latestcomponentpractice.todo_app.model.Pen

@Dao
interface PenDAO {

    @Insert
    suspend fun addPen(pen : Pen) : Long

    @Query("SELECT * FROM pen")
    fun getPens() : LiveData<MutableList<Pen>>

    @Delete
    suspend fun deletePen(pen: Pen)

    @Update
    suspend fun updatePen(pen: Pen)

    @Query("DELETE FROM pen")
    suspend fun deleteAll()

    @Query("SELECT * FROM pen WHERE price <= :price")
    fun getFilteredPen(price : Double) : LiveData<MutableList<Pen>>

}