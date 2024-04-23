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
        Log.d("AAA", "ProjectsViewModel init")
    }

    private fun checkContain(project: Project): Boolean { // check contains already project in
        return _projects.value?.contains(project) == true
    }

    fun addProject(nameProject: String): Boolean {
        val project = Project(name = nameProject)
        return if (!checkContain(project)) {
            Thread {
                db.getDao().insertProject(project)
            }.start()
            true
        } else false
    }

    fun editProject(indexEdit: Int, nameProject: String): Boolean {
        val project = Project(name = nameProject)
        return if (!checkContain(project)) {
            val updateProjects = _projects.value
            updateProjects?.get(indexEdit)?.setName(nameProject)
            _projects.value = updateProjects!!
            true
        } else false
    }

    fun deleteProject(project: Project) {
        Thread {
            db.getDao().deleteProject(project)
        }.start()
//        _projects.value = _projects.value?.filterIndexed { index, _ -> index != indexRemove}
    }

    fun getNameProject(index: Int): String {
        return _projects.value!![index].getName()
    }

    fun updateList(list: List<Project>) {
        _projects.value = list
    }
}