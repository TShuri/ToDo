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
           syncProjects()
        }
    }

    private suspend fun syncProjects() {
        try {
            val remoteProjects = RetrofitInstance.apiService.getProjects()
            db.getDao().insertProjects(remoteProjects)

            db.getDao().getAllProject().collect { projects ->
                _projects.value = projects
            }
        } catch (e: Exception) {
            Log.e("ProjectsViewModel", "Error syncing projects", e)
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
            viewModelScope.launch {
                try {
                    val newProject = Project(name = nameProject)
                    RetrofitInstance.apiService.createProject(newProject)
                    syncProjects()
                } catch (e: Exception) {
                    Log.e("ProjectsViewModel", "Error adding project", e)
                }
            }
            true
        } else false
    }

    fun editProject(existProject: Project, newName: String): Boolean {
        return if (!checkContain(newName)) {
            viewModelScope.launch {
                try {
                    existProject.setName(newName)
                    RetrofitInstance.apiService.updateProject(existProject.getId()!!, existProject)
                    syncProjects()
                } catch (e: Exception) {
                    Log.e("ProjectsViewModel", "Error editing project", e)
                }
            }
            true
        } else false
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch {
            RetrofitInstance.apiService.deleteProject(project.getId()!!)
            db.getDao().deleteProject(project)
            syncProjects()
        }
    }
}