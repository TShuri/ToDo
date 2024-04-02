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

    init {
        Log.d("AAA", "TasksViewModel init")
    }

    fun addTask(name: String, mark: String, date: String, priority: String) {
        Log.d("BBB", date)
        val task: Task = Task(name, mark, date, priority)

        _tasks.value = (_tasks.value ?: emptyList()) + task
    }

    fun resetCurrentTask() {
        _currentTask.value = null
    }
}