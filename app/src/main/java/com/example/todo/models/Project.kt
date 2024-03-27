package com.example.todo.models

data class Project(private var name: String) {
    fun getName(): String {
        return name
    }

    fun setName(newName: String) {
        name = newName
    }
}