package com.example.todo.interfaces

import com.example.todo.models.Task

interface ItemTaskClick {
    fun navigateToTask(task: Task)
    fun changeStatus(index: Int)
}