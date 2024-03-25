package com.example.latestcomponentpractice.todo_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @ColumnInfo(name = "person_name")
    val name : String,

    @ColumnInfo(name = "person_age")
    val age : Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    val id : Int = 0,
)
