package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.models.Task

class TasksViewModel: ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _currentTask = MutableLiveData<Task?>()
    val currentTask: LiveData<Task?> = _currentTask

    private val _indexCurrentTask = MutableLiveData<Int?>()
    val indexCurrentTask: LiveData<Int?> = _indexCurrentTask

    init {
        Log.d("AAA", "TasksViewModel init")
    }

    fun addTask(task: Task) {
        _tasks.value = (_tasks.value ?: emptyList()) + task
    }

    fun editTask(task: Task, index: Int) {
        val updateTasks = _tasks.value
        updateTasks?.get(index)?.change(task)
        _tasks.value = updateTasks!!
    }

    fun changeStatus(index: Int) {
        val updateTasks = _tasks.value
        updateTasks?.get(index)?.changeStatus()
        _tasks.value = updateTasks!!
    }

    fun updateCurrentTask(task: Task, index: Int) {
        _currentTask.value = task
        _indexCurrentTask.value = index
    }

    fun resetCurrentTask() {
        _currentTask.value = null
        _indexCurrentTask.value = null
    }
}