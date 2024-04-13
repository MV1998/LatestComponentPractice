package com.example.latestcomponentpractice.todo_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pen")
data class Pen(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "is_ball_pen")
    val isBallPen: Boolean,

    @ColumnInfo(name = "price")
    val price : Double
)
