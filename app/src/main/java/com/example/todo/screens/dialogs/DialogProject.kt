package com.example.todo.screens.dialogs

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.todo.MAIN
import com.example.todo.R
import com.example.todo.databinding.DialogProjectBinding
import com.example.todo.models.Project
import com.example.todo.viewmodels.ProjectsViewModel

class DialogProject(private val project: Project? = null) : BottomSheetDialogFragment() {
    private var _binding: DialogProjectBinding? = null
    private val binding get() = _binding!!
    private val projectsViewModel: ProjectsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.findViewById<RecyclerView>(R.id.dialog_project)?.layoutManager = LinearLayoutManager(context)

        if (project != null) {
            binding.editTextNameProject.setText(project.getName())
        }

        binding.buttonConfirmProject.setOnClickListener {
            val nameProject = binding.editTextNameProject.text.toString()

            if (nameProject == "") { // check zero name
                val toast = Toast.makeText(MAIN, "Название не может быть пустым!", Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                if (project != null) { // if project then edit
                    editProject(project, nameProject)
                } else {
                    addProject(nameProject)
                }
            }
        }
    }

    private fun editProject(existProject: Project, newName: String) {
        if (!projectsViewModel.editProject(existProject, newName)) {
            val toast = Toast.makeText(MAIN, "Уже имеется проект с таким названием!", Toast.LENGTH_SHORT)
            toast.show()
        } else dismiss()
    }

    private fun addProject(nameProject: String) {
        if (!projectsViewModel.addProject(nameProject)) {
            val toast = Toast.makeText(MAIN, "Уже имеется проект с таким названием!", Toast.LENGTH_SHORT)
            toast.show()
        } else dismiss()
    }
}