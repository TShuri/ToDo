package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.interfaces.ItemTaskClick
import com.example.todo.models.Task

class TasksAdapter(private var tasks: List<Task>,
                   private val clickListener: ItemTaskClick): RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardItem: CardView = itemView.findViewById(R.id.card_item_task)
        val textName: TextView = itemView.findViewById(R.id.text_name_task)
        val textMark: TextView = itemView.findViewById(R.id.text_mark)
        val textDate: TextView = itemView.findViewById(R.id.text_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task = tasks[position]

        holder.textName.text = task.name
        holder.textDate.text = task.date
        holder.textMark.text = task.mark
//        holder.cardItem.setBackgroundColor()
    }
}