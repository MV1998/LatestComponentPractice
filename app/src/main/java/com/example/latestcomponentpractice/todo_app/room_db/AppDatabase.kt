package com.example.latestcomponentpractice.todo_app.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.latestcomponentpractice.todo_app.model.Pen
import com.example.latestcomponentpractice.todo_app.model.Person

@Database(entities = [Person::class, Pen::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDAO() : PersonDAO

    abstract fun penDAO() : PenDAO

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "ApplicationDB")
//                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}