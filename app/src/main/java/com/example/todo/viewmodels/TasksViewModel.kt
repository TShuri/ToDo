package com.example.todo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.db
import com.example.todo.models.Project
import com.example.todo.models.Task
import com.example.todo.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _currentTask = MutableLiveData<Task?>()
    val currentTask: LiveData<Task?> = _currentTask

    fun initTasks(projectId: Int) {
        viewModelScope.launch {
            try {
                val remoteTasks = RetrofitInstance.apiService.getTasks(projectId)
                db.getDao().insertTasks(remoteTasks)
                db.getDao().getAllTask(projectId).collect { tasksList ->
                    _tasks.postValue(tasksList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the exception, possibly by loading tasks only from local database
                db.getDao().getAllTask(projectId).collect { tasksList ->
                    _tasks.postValue(tasksList)
                }
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                val newTask = RetrofitInstance.apiService.createTask(task)
                db.getDao().insertTask(newTask)
                initTasks(task.project_id)
            } catch (e: Exception) {
                Log.d("TaskViewModel", "addTask", e)
                // Handle the exception, maybe fallback to only local operations
                db.getDao().insertTask(task)
                initTasks(task.project_id)
            }
        }
    }

    fun editTask(changedTask: Task) {
        _currentTask.value?.change(changedTask)
        viewModelScope.launch {
            try {
                val updatedTask = RetrofitInstance.apiService.updateTask(_currentTask.value!!.id!!, _currentTask.value!!)
                db.getDao().updateTask(updatedTask)
                initTasks(changedTask.project_id)
            } catch (e: Exception) {
                Log.d("TaskViewModel", "editTask", e)
                // Handle the exception, maybe fallback to only local operations
                db.getDao().updateTask(changedTask)
                initTasks(changedTask.project_id)
            }
        }
    }

    fun changeStatus(index: Int) {
        val task = _tasks.value?.get(index)
        task?.changeStatus()
        viewModelScope.launch {
            try {
                val updatedTask = RetrofitInstance.apiService.updateTask(task!!.id!!, task!!)
                db.getDao().updateTask(updatedTask)
                initTasks(task.project_id)
            } catch (e: Exception) {
                Log.d("TaskViewModel", "editTask", e)
                // Handle the exception, maybe fallback to only local operations
                db.getDao().updateTask(task!!)
                initTasks(task.project_id)
            }
        }
    }

    fun updateCurrentTask(task: Task) {
        _currentTask.value = task
    }

    fun resetCurrentTask() {
        _currentTask.value = null
    }
}