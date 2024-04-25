package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.db
import com.example.todo.models.Project
import com.example.todo.models.Task
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _currentTask = MutableLiveData<Task?>()
    val currentTask: LiveData<Task?> = _currentTask

//    private val _indexCurrentTask = MutableLiveData<Int?>()
//    val indexCurrentTask: LiveData<Int?> = _indexCurrentTask

    fun addTask(task: Task) {
        viewModelScope.launch {
            db.getDao().insertTask(task)
        }
    }

    fun editTask(changedTask: Task) {
        currentTask.value?.change(changedTask)
        viewModelScope.launch {
            db.getDao().updateTask(currentTask.value!!)
        }
    }

    fun changeStatus(index: Int) {
        val updateTasks = _tasks.value
        updateTasks?.get(index)?.changeStatus()
        _tasks.value = updateTasks!!
    }

    fun updateCurrentTask(task: Task, index: Int) {
        _currentTask.value = task
//        _indexCurrentTask.value = index
    }

    fun resetCurrentTask() {
        _currentTask.value = null
//        _indexCurrentTask.value = null
    }

    fun updateList(list: List<Task>) {
        _tasks.value = list
    }
}