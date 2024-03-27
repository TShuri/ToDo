package com.example.todo.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.ItemListDialogFragment
import com.example.todo.MAIN
import com.example.todo.R
import com.example.todo.adapters.ProjectsRecyclerAdapter
import com.example.todo.databinding.FragmentProjectsBinding
import com.example.todo.interfaces.ItemProjectClick
import com.example.todo.viewmodels.CurrentProjectViewModel
import com.example.todo.viewmodels.ProjectsViewModel

class FragmentProjects : Fragment(), ItemProjectClick {

    private lateinit var binding: FragmentProjectsBinding
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private val currentProjectViewModel: CurrentProjectViewModel by activityViewModels()
    private lateinit var projectsRecyclerAdapter: ProjectsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProjectsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listProjects.layoutManager = LinearLayoutManager(activity)

        projectsViewModel.projects.observe(activity as LifecycleOwner) {
            projectsRecyclerAdapter = ProjectsRecyclerAdapter(it, this)
            binding.listProjects.adapter = projectsRecyclerAdapter
        }

        binding.buttonCreateProject.setOnClickListener {
            val dialog = ItemListDialogFragment()
            dialog.show(MAIN.supportFragmentManager, "Dialog")
        }
    }

    override fun editItemProject(index: Int, nameProject: String) {
        val dialog = ItemListDialogFragment(index)
        dialog.show(MAIN.supportFragmentManager, "Dialog")
    }

    override fun deleteItemProject(index: Int) {
        projectsViewModel.deleteProject(index)
    }

    override fun navigateToProject(nameProject: String) {
        currentProjectViewModel.change(nameProject)
        MAIN.navController.navigate(R.id.action_fragmentProjects_to_fragmentTasks)
    }
}