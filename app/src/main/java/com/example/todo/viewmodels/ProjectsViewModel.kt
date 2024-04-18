package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.models.Project

class ProjectsViewModel: ViewModel() {
    private val _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    init {
        Log.d("AAA", "ProjectsViewModel init")
    }

    private fun checkContain(nameProject: String): Boolean {
        return _projects.value?.contains(Project(nameProject)) == true
    }

    fun addProject(nameProject: String): Boolean {
        return if (!checkContain(nameProject)) {
            _projects.value = (_projects.value ?: emptyList()) + Project(nameProject)
            true
        } else false
    }

    fun editProject(indexEdit: Int, nameProject: String): Boolean {
        return if (!checkContain(nameProject)) {
            val updateProjects = _projects.value
            updateProjects?.get(indexEdit)?.setName(nameProject)
            _projects.value = updateProjects!!
            true
        } else false
    }

    fun deleteProject(indexRemove: Int) {
        _projects.value = _projects.value?.filterIndexed { index, _ -> index != indexRemove}
    }

    fun getNameProject(index: Int): String {
        return _projects.value!![index].getName()
    }
}