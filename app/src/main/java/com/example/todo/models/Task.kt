package com.example.todo.models

data class Task(var name: String, var description: String? = null, var mark: String? = null,
                var date: String? = null, var priority: String? = null, var status: Boolean? = false) {
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