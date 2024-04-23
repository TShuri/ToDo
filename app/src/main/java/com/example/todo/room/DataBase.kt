package com.example.todo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.models.Project

@Database (entities = [Project::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): Dao
    companion object {
        fun getDb(context: Context): DataBase {
            return Room.databaseBuilder(context.applicationContext,
                DataBase::class.java,
                "database"
                ).build()
        }
    }
}