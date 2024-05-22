package com.example.todo.interfaces

import com.example.todo.models.Project

interface ItemProjectClick {
    fun editItemProject(project: Project)
    fun deleteItemProject(project: Project)
    fun navigateToProject(project: Project)
}