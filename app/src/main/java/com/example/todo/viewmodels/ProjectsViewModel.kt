package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.db
import com.example.todo.models.Project
import com.example.todo.retrofit.RetrofitInstance
import kotlinx.coroutines.launch


class ProjectsViewModel: ViewModel() {
    private val _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    init {
        viewModelScope.launch {
            try {
                syncProjects()
            } catch (e: Exception) {
                // Обработка ошибок
                Log.d("AAA", e.toString())
            }
        }
    }

    private suspend fun syncProjects() {
        // Fetch projects from remote
        val remoteProjects = RetrofitInstance.apiService.getProjects()
        // Save to local database
        db.getDao().insertProjects(remoteProjects)
        // Fetch from local database and update LiveData
        db.getDao().getAllProject().collect { projects ->
            _projects.value = projects
        }
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
            viewModelScope.launch {
                db.getDao().insertProject(project)
            }
            true
        } else false
    }

    fun editProject(existProject: Project, newName: String): Boolean {
        return if (!checkContain(newName)) {
            existProject.setName(newName)
            viewModelScope.launch {
                db.getDao().updateProject(existProject)
            }
            true
        } else false
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch {
            db.getDao().deleteProject(project)
        }
    }
}