package com.example.todo.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MAIN
import com.example.todo.R
import com.example.todo.interfaces.ItemTaskClick
import com.example.todo.models.Task

class TasksAdapter(private var tasks: List<Task>,
                   private val clickListener: ItemTaskClick): RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardItem: CardView = itemView.findViewById(R.id.card_item_task)
        val cardMark: CardView = itemView.findViewById(R.id.card_mark)

        val buttonDone: ImageButton = itemView.findViewById(R.id.button_done)

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

        if (task.mark == "") {
            holder.cardMark.visibility = View.GONE
        } else {
            holder.textMark.text = task.mark
        }

        if (task.date == "") {
            holder.textDate.visibility = View.GONE
        } else {
            holder.textDate.text = task.date
        }

//        holder.cardItem.setBackgroundColor()


        holder.buttonDone.setOnClickListener {
            clickListener.changeStatus(position)
        }

        holder.cardItem.setOnClickListener {
            clickListener.navigateToTask(task, position)
        }

        if (task.status == true) {
            holder.buttonDone.setImageResource(R.drawable.baseline_check_box)
            holder.buttonDone.setColorFilter(R.color.gray_noactive)

            val color = ContextCompat.getColor(MAIN, R.color.gray_noactive)
            holder.textName.setTextColor(color)
            holder.textMark.setTextColor(color)
            holder.textDate.setTextColor(color)
        }
    }
}