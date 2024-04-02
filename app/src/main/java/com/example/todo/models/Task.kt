package com.example.todo.models

data class Task(var name: String, var mark: String? = null, var date: String? = null,
                var priority: String? = null, var status: Boolean? = false) {

}