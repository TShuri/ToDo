package com.example.todo.screens.dialogs

import android.os.Bundle
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
import com.example.todo.viewmodels.ProjectsViewModel

class DialogProject(private val index: Int? = null) : BottomSheetDialogFragment() {
    private var _binding: DialogProjectBinding? = null
    private val binding get() = _binding!!
    private val projectsViewModel: ProjectsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.findViewById<RecyclerView>(R.id.dialog_project)?.layoutManager = LinearLayoutManager(context)

        if (index != null) {
            binding.editTextNameProject.setText(projectsViewModel.getNameProject(index))
        }

        binding.buttonConfirmProject.setOnClickListener {
            val nameProject = binding.editTextNameProject.text.toString()
            if (nameProject == "") { // check zero name
                val toast = Toast.makeText(MAIN, "Название не может быть пустым!", Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                if (index != null) { // if index then edit
                    editProject(index, nameProject)
                } else {
                    addProject(nameProject)
                }
            }
        }
    }

    private fun editProject(index: Int, nameProject: String) {
        if (!projectsViewModel.editProject(index, nameProject)) {
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