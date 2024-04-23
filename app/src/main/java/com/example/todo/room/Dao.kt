package com.example.todo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.models.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Delete
    fun deleteProject(project: Project)

    @Query("SELECT * FROM projects")
    fun getAllProject(): Flow<List<Project>>
}