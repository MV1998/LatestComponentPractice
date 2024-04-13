package com.example.latestcomponentpractice.todo_app.repository

import androidx.lifecycle.LiveData
import com.example.latestcomponentpractice.todo_app.model.Pen
import com.example.latestcomponentpractice.todo_app.room_db.PenDAO

class PenRepository(private val penDAO: PenDAO) {
    val pens = penDAO.getPens()

    suspend fun addPen(pen: Pen) : Long {
        return penDAO.addPen(pen)
    }

    suspend fun getFiltered(price : Double) : LiveData<MutableList<Pen>> {
        return penDAO.getFilteredPen(price)
    }

}