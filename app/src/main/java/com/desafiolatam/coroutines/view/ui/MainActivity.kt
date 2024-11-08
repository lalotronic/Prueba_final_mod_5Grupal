package com.desafiolatam.coroutines.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiolatam.coroutines.R
import com.desafiolatam.coroutines.data.TaskEntity
import com.desafiolatam.coroutines.databinding.ActivityMainBinding
import com.desafiolatam.coroutines.view.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
/*class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()

    @Inject
    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTaskList()
    }

    private fun getTaskList() {
        lifecycleScope.launchWhenResumed {
            viewModel.taskListStateFlow.collectLatest {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(taskList: List<TaskEntity>) {
        adapter = TaskAdapter()
        adapter.taskList = taskList
        binding.rvTask.layoutManager = LinearLayoutManager(this)
        binding.rvTask.adapter = adapter
    }
}*/

class MainActivity : AppCompatActivity(),Fab,OnClickListener {

    lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()


    @Inject
    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clAddTask.setOnClickListener(this)

        getTaskList()
    }

    private fun getTaskList() {
        lifecycleScope.launchWhenResumed {
            viewModel.taskListStateFlow.collectLatest {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(taskList: List<TaskEntity>) {
        adapter = TaskAdapter()
        adapter.taskList = taskList
        binding.rvTask.layoutManager = LinearLayoutManager(this)
        binding.rvTask.adapter = adapter
        //vamos con la eliminacion
        adapter.onLongClick = {
            Toast.makeText(this, "borrar tarea", Toast.LENGTH_LONG).show()
            borrar(it)

        }
    }

    private fun borrar(it: TaskEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.deleteTask(it)
        }

    }


    override fun onClick(view: View) {
        when (view.id){
            R.id.cl_add_task  -> startActivity(Intent(this,NewTaskActivity::class.java))
        }
    }
}
