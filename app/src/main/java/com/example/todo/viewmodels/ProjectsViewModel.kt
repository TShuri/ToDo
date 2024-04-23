package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todo.db
import com.example.todo.models.Project

class ProjectsViewModel: ViewModel() {
    private val _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    init {
//        Log.d("AAA", "ProjectsViewModel init")
    }

    private fun checkContain(nameProject: String): Boolean { // проверка на существование уже такого проекта по его названию
        for (_project in _projects.value!!) {
            if (_project.getName() == nameProject) return true
        }
        return false
    }

    fun addProject(nameProject: String): Boolean {
        return if (!checkContain(nameProject)) {
            val project = Project(name = nameProject)
            Thread {
                db.getDao().insertProject(project)
            }.start()
            true
        } else false
    }

    fun editProject(existProject: Project, newName: String): Boolean {
        return if (!checkContain(newName)) {
            existProject.setName(newName)
            Thread {
                db.getDao().updateProject(existProject)
            }.start()
            true
        } else false
    }

    fun deleteProject(project: Project) {
        Thread {
            db.getDao().deleteProject(project)
        }.start()
    }

    fun updateList(list: List<Project>) {
        _projects.value = list
    }
}