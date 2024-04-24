package com.example.todo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity (tableName = "tasks",
         foreignKeys = [ForeignKey(entity = Project::class, parentColumns = ["id"], childColumns = ["project_id"], onDelete = CASCADE)])
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "mark")
    var mark: String? = null,
    @ColumnInfo(name = "date")
    var date: String? = null,
    @ColumnInfo(name = "priority")
    var priority: String? = null,
    @ColumnInfo(name = "status")
    var status: Boolean? = false,
    @ColumnInfo(name = "project_id")
    var project_id: Int)
{
    fun change(changedtask: Task) {
        this.name = changedtask.name
        this.description = changedtask.description
        this.mark = changedtask.mark
        this.date = changedtask.date
        this.priority = changedtask.priority
    }

    fun changeStatus() {
        this.status = this.status == false
    }
}