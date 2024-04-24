package com.example.todo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.models.Project
import com.example.todo.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    // sql for projects
    @Insert
    fun insertProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Delete
    fun deleteProject(project: Project)

    @Query("SELECT * FROM projects")
    fun getAllProject(): Flow<List<Project>>

    // sql for tasks
    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE project_id = :id_project")
    fun getAllTask(id_project: Int): Flow<List<Task>>
}