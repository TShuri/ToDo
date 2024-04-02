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
import com.example.todo.interfaces.ItemTaskClick
import com.example.todo.models.Task
import com.example.todo.viewmodels.TasksViewModel

class FragmentTask: Fragment() {
    private lateinit var binding: FragmentTaskBinding

    private val tasksViewModel: TasksViewModel by activityViewModels()
    private var indexCurrentTask: Int? = null

    private lateinit var name: String
    private lateinit var description: String
    private lateinit var mark: String
    private lateinit var date: String
    private lateinit var priority: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isEdit: Boolean = checkIsEdit()

        binding.buttonReady.setOnClickListener {
            if (!checkEmptyName()) {
                name = binding.editTextName.text.toString()
                description = binding.editTextDescription.text.toString()
                mark = binding.editTextMark.text.toString()
                date = binding.editTextDate.text.toString()
                priority = binding.textPriority.text.toString()

                val task = Task(name, description, mark, date, priority)

                if (isEdit) {
                    tasksViewModel.editTask(task, indexCurrentTask!!)
                } else{
                    tasksViewModel.addTask(task)
                }

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

    private fun checkIsEdit(): Boolean {
        val currentTask = tasksViewModel.currentTask.value

        if (currentTask != null) {
            indexCurrentTask = tasksViewModel.indexCurrentTask.value!!.toInt()

            binding.editTextName.setText(currentTask.name)
            binding.editTextDescription.setText(currentTask.description)
            binding.editTextMark.setText(currentTask.mark)
            binding.editTextDate.setText(currentTask.date)

            return true
        } else
            return false
    }
}