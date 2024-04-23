package com.example.todo.interfaces

import com.example.todo.models.Project

interface ItemProjectClick {
    fun editItemProject(index: Int, nameProject: String)
    fun deleteItemProject(project: Project)
    fun navigateToProject(nameProject: String)
}