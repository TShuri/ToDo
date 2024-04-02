package com.example.todo.interfaces

import com.example.todo.models.Task

interface ItemTaskClick {
    fun navigateToTask(toTask: Task, indexTask: Int)
    fun changeStatus(index: Int)
}