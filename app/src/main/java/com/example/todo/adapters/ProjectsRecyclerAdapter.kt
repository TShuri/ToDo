package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.interfaces.ItemProjectClick
import com.example.todo.models.Project

class ProjectsRecyclerAdapter(private var projects: List<Project>, private val clickListener: ItemProjectClick) : RecyclerView.Adapter<ProjectsRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameProjectTextView: TextView = itemView.findViewById(R.id.text_name_item_project)
        val buttonDelete: ImageButton = itemView.findViewById(R.id.button_delete_item_project)
        val buttonEdit: ImageButton = itemView.findViewById(R.id.button_edit_item_project)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project: Project = projects[position]

        holder.nameProjectTextView.text = project.getName()

        holder.nameProjectTextView.setOnClickListener {
            clickListener.navigateToProject(project.getName())
        }

        holder.buttonDelete.setOnClickListener {
            clickListener.deleteItemProject(position)
        }

        holder.buttonEdit.setOnClickListener {
            clickListener.editItemProject(position, project.getName())
        }

    }
}