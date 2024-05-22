package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.models.Project

class CurrentProjectViewModel: ViewModel() {
    private val _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> = _currentProject

    fun change(project: Project) {
        _currentProject.value = project
    }
}