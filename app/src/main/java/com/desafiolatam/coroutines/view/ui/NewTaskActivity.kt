package com.desafiolatam.coroutines.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.coroutines.R
import com.desafiolatam.coroutines.data.TaskEntity
import com.desafiolatam.coroutines.databinding.ActivityNewTaskBinding
import com.desafiolatam.coroutines.view.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
/*class NewTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
*/
class NewTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewTaskBinding
    private val viewModel: TaskViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener{
            agregarTarea()
        }

    }

    private fun agregarTarea() {
        var task : TaskEntity = TaskEntity(0,"title","cualquiercosa")
        //llamando a couroutine
        lifecycleScope.launch(Dispatchers.IO) {
        viewModel.addTask(task)
    }
        Toast.makeText(this, "Agregando tarea", Toast.LENGTH_LONG).show()
        startActivity(Intent(this,MainActivity::class.java))
    }


}