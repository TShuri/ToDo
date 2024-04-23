package com.example.todo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo (name = "name")
    private var name: String)
{
    fun getName(): String {
        return name
    }

    fun setName(newName: String) {
        name = newName
    }
}