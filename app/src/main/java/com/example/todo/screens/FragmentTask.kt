package com.example.todo.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todo.MAIN
import com.example.todo.R
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.viewmodels.TasksViewModel

class FragmentTask: Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private val tasksViewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isEdit()

        binding.buttonReady.setOnClickListener {
            if (!checkEmptyName()) {
                addTask()
                MAIN.navController.navigate(R.id.action_fragmentTask_to_fragmentTasks)
            }
            else {
                val toast = Toast.makeText(MAIN, "Название не может быть пустым!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun checkEmptyName():Boolean {
        return binding.editTextName.text.toString() == ""
    }

    private fun addTask() {
        val name = binding.editTextName.text.toString()
        var mark = binding.editTextMark.text.toString()
        var date = binding.editTextDate.text.toString()
        var priority = binding.textPriority.text.toString()

        tasksViewModel.addTask(name, mark, date, priority)
    }

    private fun isEdit() {
        val currentTask = tasksViewModel.currentTask.value

        if (currentTask != null) {
            binding.editTextName.setText(currentTask.name)
            binding.editTextMark.setText(currentTask.mark)
            binding.editTextDate.setText(currentTask.date)
        }
    }
}