package com.example.latestcomponentpractice.todo_app.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.latestcomponentpractice.todo_app.model.Person

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {

    abstract val personDAO : PersonDAO

    companion object {
        @Volatile
        private var INSTANCE : PersonDatabase? = null

        fun getInstance(context: Context) : PersonDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, PersonDatabase::class.java, "ApplicationDB").build()
                }
                return instance
            }
        }
    }
}