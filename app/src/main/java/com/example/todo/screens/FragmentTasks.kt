package com.example.todo.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.MAIN
import com.example.todo.R
import com.example.todo.adapters.TasksAdapter
import com.example.todo.databinding.FragmentTasksBinding
import com.example.todo.interfaces.ItemTaskClick
import com.example.todo.viewmodels.CurrentProjectViewModel
import com.example.todo.viewmodels.ProjectsViewModel
import com.example.todo.viewmodels.TasksViewModel

class FragmentTasks : Fragment(), ItemTaskClick {

    private lateinit var binding: FragmentTasksBinding
    private val currentProjectViewModel: CurrentProjectViewModel by activityViewModels()
    private val tasksViewModel: TasksViewModel by activityViewModels()
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentProjectViewModel.currentProject.observe(activity as LifecycleOwner) {
            binding.textTitleTasks.text = it
        }

        binding.listTasks.layoutManager = LinearLayoutManager(activity)

        tasksViewModel.tasks.observe(activity as LifecycleOwner) {
            tasksAdapter = TasksAdapter(it, this)
            binding.listTasks.adapter = tasksAdapter
        }

        binding.buttonCreateTask.setOnClickListener {
            tasksViewModel.resetCurrentTask()
            MAIN.navController.navigate(R.id.action_fragmentTasks_to_fragmentTask)
        }
    }

    override fun navigateToTask(nameTask: String) {
        TODO("Not yet implemented")
    }

}