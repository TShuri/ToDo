package com.example.todo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.room.DataBase
import com.example.todo.viewmodels.CurrentProjectViewModel
import com.example.todo.viewmodels.ProjectsViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    val projectsViewModel: ProjectsViewModel by viewModels()
    val currentProjectViewModel: CurrentProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        MAIN = this
        db = DataBase.getDb(this)

        db.getDao().getAllProject().asLiveData().observe(this) {
            projectsViewModel.updateList(it)
        }
    }
}