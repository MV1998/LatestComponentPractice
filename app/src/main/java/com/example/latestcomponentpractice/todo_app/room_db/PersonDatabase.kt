package com.example.latestcomponentpractice.todo_app.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.latestcomponentpractice.todo_app.model.Person

@Database(entities = [Person::class], version = 2)
abstract class PersonDatabase : RoomDatabase() {

    abstract val personDAO : PersonDAO

    companion object {
        @Volatile
        private var INSTANCE : PersonDatabase? = null

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE person ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }


        fun getInstance(context: Context) : PersonDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, PersonDatabase::class.java, "ApplicationDB").addMigrations(migrations = arrayOf(
                        migration_1_2)).build()
                }
                return instance
            }
        }
    }
}