package com.example.todo.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.todo.databinding.FragmentTasksBinding
import com.example.todo.viewmodels.CurrentProjectViewModel
import com.example.todo.viewmodels.ProjectsViewModel

class FragmentTasks : Fragment() {

    lateinit var binding: FragmentTasksBinding
    private val currentProjectViewModel: CurrentProjectViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentProjectViewModel.currentProject.observe(activity as LifecycleOwner) {
            binding.textTitleTasks.text = it
        }
    }

}