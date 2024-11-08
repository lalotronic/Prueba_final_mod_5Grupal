package com.desafiolatam.coroutines.view.ui

import android.graphics.Paint
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.desafiolatam.coroutines.data.TaskEntity
import com.desafiolatam.coroutines.databinding.ItemTaskBinding

/*class TaskAdapter :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private lateinit var binding: ItemTaskBinding
    lateinit var taskList: List<TaskEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun onBind(task: TaskEntity) {
            binding.run {
                tvTaskTitle.text = task.title
                tvTaskDescription.text = task.description
            }
        }
    }
}*/

class TaskAdapter :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private lateinit var binding: ItemTaskBinding
    lateinit var taskList: List<TaskEntity>
    var onLongClick: ((TaskEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(binding: ItemTaskBinding) : ViewHolder(binding.root) {

        fun onBind(task: TaskEntity) {

            binding.run {
                tvTaskTitle.text = task.title
                tvTaskDescription.text = task.description

                    clItem.setOnClickListener{
                        onLongClick?.invoke(task)
                        true
                    }
                cbIsCompleted.setOnClickListener{
                    // Aplicar el flag de tachado al TextView
                    tvTaskTitle.paintFlags = tvTaskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    // Mostrar el Toast
                    Toast.makeText(binding.root.context, "borrar tarea", Toast.LENGTH_LONG).show()




                }


            }
        }


        }
    }

/*private fun CheckBox.setOnClickListener(action: () -> Unit) {
    this.setOnClickListener {
        action() // Llama a la acción proporcionada cuando se hace clic
    }*/

