package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.models.Project

class CurrentProjectViewModel: ViewModel() {
    private val _currentProject = MutableLiveData<String>()
    val currentProject: LiveData<String> = _currentProject

    init {
        //_currentProject.value = String()
        Log.d("AAA", "CurrentProjectViewModel init")
    }

    fun change(name: String) {
        _currentProject.value = name
    }
}